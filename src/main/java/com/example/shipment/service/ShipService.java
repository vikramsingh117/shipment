package com.example.shipment.service;

import com.example.shipment.api.dto.ShipCreateRequest;
import com.example.shipment.api.dto.ShipResponse;
import com.example.shipment.api.dto.ShipUpdateRequest;
import com.example.shipment.domain.Ship;
import com.example.shipment.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ShipService {

    private final ShipRepository repository;

    public ShipService(ShipRepository repository) {
        this.repository = repository;
    }

    public List<ShipResponse> listShips() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ShipResponse createShip(ShipCreateRequest request) {
        OffsetDateTime now = OffsetDateTime.now();
        Ship ship = Ship.builder()
                .name(request.getName())
                .email(request.getEmail())
                .createdAt(now)
                .updatedAt(now)
                .build();
        Ship saved = repository.save(ship);
        return toResponse(saved);
    }

    public ShipResponse getShip(Long id) {
        Ship ship = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Ship not found"));
        return toResponse(ship);
    }

    public ShipResponse updateShip(Long id, ShipUpdateRequest request) {
        Ship existing = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Ship not found"));
        existing.setName(request.getName());
        existing.setEmail(request.getEmail());
        existing.setUpdatedAt(OffsetDateTime.now());
        Ship saved = repository.save(existing);
        return toResponse(saved);
    }

    public void deleteShip(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Ship not found");
        }
        repository.deleteById(id);
    }

    private ShipResponse toResponse(Ship ship) {
        return ShipResponse.builder()
                .id(ship.getId())
                .name(ship.getName())
                .email(ship.getEmail())
                .createdAt(ship.getCreatedAt())
                .updatedAt(ship.getUpdatedAt())
                .build();
    }
}


