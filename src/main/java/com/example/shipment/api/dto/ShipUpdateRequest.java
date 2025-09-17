package com.example.shipment.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ShipUpdateRequest {
    @NotBlank(message = "name is required")
    private String name;

    @Email(message = "email must be valid")
    private String email;
}


