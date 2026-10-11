package com.example.logistics.shipment;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
@Entity @Table(name="shipments")
public class Shipment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="tracking_number",nullable=false,unique=true,length=32) private String trackingNumber;
 @Column(name="sender_name",nullable=false) private String senderName;
 @Column(name="recipient_name",nullable=false) private String recipientName;
 @Column(nullable=false) private String origin;
 @Column(nullable=false) private String destination;
 @Column(name="weight_kg",nullable=false,precision=9,scale=3) private BigDecimal weightKg;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal price;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ShipmentStatus status;
 @Column(name="created_at",nullable=false) private Instant createdAt;
 @Column(name="updated_at",nullable=false) private Instant updatedAt;
 protected Shipment() {}
 public Shipment(String tracking,String sender,String recipient,String origin,String destination,BigDecimal weight,BigDecimal price) {
  this.trackingNumber=tracking; this.senderName=sender; this.recipientName=recipient; this.origin=origin; this.destination=destination;
  this.weightKg=weight; this.price=price; this.status=ShipmentStatus.BOOKED; this.createdAt=Instant.now(); this.updatedAt=createdAt;
 }
 @PreUpdate void touch(){updatedAt=Instant.now();}
 public Long getId(){return id;} public String getTrackingNumber(){return trackingNumber;} public String getSenderName(){return senderName;}
 public String getRecipientName(){return recipientName;} public String getOrigin(){return origin;} public String getDestination(){return destination;}
 public BigDecimal getWeightKg(){return weightKg;} public BigDecimal getPrice(){return price;} public ShipmentStatus getStatus(){return status;}
 public Instant getCreatedAt(){return createdAt;} public Instant getUpdatedAt(){return updatedAt;}
 public void setStatus(ShipmentStatus status){this.status=status;}
}