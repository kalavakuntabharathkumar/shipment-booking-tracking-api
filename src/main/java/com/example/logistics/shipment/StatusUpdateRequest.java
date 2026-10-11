package com.example.logistics.shipment;
import jakarta.validation.constraints.NotNull;
public record StatusUpdateRequest(@NotNull ShipmentStatus status) {}