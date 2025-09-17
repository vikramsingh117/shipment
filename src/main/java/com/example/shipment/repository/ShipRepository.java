package com.example.shipment.repository;

import com.example.shipment.domain.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Long> {
}


