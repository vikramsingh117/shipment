package com.example.shipment.api;

import com.example.shipment.api.dto.ShipCreateRequest;
import com.example.shipment.api.dto.ShipResponse;
import com.example.shipment.api.dto.ShipUpdateRequest;
import com.example.shipment.service.ShipService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ships")
public class ShipController {

    private final ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping
    public ResponseEntity<List<ShipResponse>> listShips() {
        return ResponseEntity.ok(shipService.listShips());
    }

    @PostMapping
    public ResponseEntity<ShipResponse> createShip(@Valid @RequestBody ShipCreateRequest request) {
        ShipResponse created = shipService.createShip(request);
        return ResponseEntity.created(URI.create("/ships/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipResponse> getShip(@PathVariable Long id) {
        return ResponseEntity.ok(shipService.getShip(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShipResponse> updateShip(@PathVariable Long id, @Valid @RequestBody ShipUpdateRequest request) {
        return ResponseEntity.ok(shipService.updateShip(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteShip(@PathVariable Long id) {
        shipService.deleteShip(id);
    }
}


