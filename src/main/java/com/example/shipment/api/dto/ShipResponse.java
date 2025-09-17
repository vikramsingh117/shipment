package com.example.shipment.api.dto;

import lombok.Builder;
import lombok.Value;

import java.time.OffsetDateTime;

@Value
@Builder
public class ShipResponse {
    Long id;
    String name;
    String email;
    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;
}


