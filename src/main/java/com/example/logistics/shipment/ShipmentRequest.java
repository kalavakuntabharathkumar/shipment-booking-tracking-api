package com.example.logistics.shipment;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record ShipmentRequest(@NotBlank @Size(max=120) String senderName,
 @NotBlank @Size(max=120) String recipientName, @NotBlank @Size(max=160) String origin,
 @NotBlank @Size(max=160) String destination, @NotNull @DecimalMin("0.001") BigDecimal weightKg) {}