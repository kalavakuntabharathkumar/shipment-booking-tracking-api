package com.example.logistics.shipment;
import java.math.BigDecimal;
import java.time.Instant;
public record ShipmentResponse(Long id,String trackingNumber,String senderName,String recipientName,String origin,String destination,
 BigDecimal weightKg,BigDecimal price,ShipmentStatus status,Instant createdAt,Instant updatedAt) {
 public static ShipmentResponse from(Shipment s){return new ShipmentResponse(s.getId(),s.getTrackingNumber(),s.getSenderName(),s.getRecipientName(),
 s.getOrigin(),s.getDestination(),s.getWeightKg(),s.getPrice(),s.getStatus(),s.getCreatedAt(),s.getUpdatedAt());}
}