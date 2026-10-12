package com.example.logistics.shipment;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
@Service
public class ShipmentService {
 private final ShipmentRepository repo; private final PricingService pricing;
 public ShipmentService(ShipmentRepository repo,PricingService pricing){this.repo=repo;this.pricing=pricing;}
 @Transactional public ShipmentResponse book(ShipmentRequest r){
  String tracking="SHP-"+UUID.randomUUID().toString().replace("-","").substring(0,12).toUpperCase();
  return ShipmentResponse.from(repo.save(new Shipment(tracking,r.senderName(),r.recipientName(),r.origin(),r.destination(),r.weightKg(),pricing.calculate(r.weightKg()))));
 }
 @Transactional(readOnly=true) public Page<ShipmentResponse> list(int page,int size){
  if(page<0||size<1||size>100)throw new IllegalArgumentException("page must be >= 0 and size must be 1..100");
  return repo.findAll(PageRequest.of(page,size,Sort.by(Sort.Direction.DESC,"createdAt"))).map(ShipmentResponse::from);
 }
 @Transactional(readOnly=true) public ShipmentResponse get(long id){return repo.findById(id).map(ShipmentResponse::from).orElseThrow(()->new ShipmentNotFoundException("Shipment not found: "+id));}
 @Transactional(readOnly=true) public ShipmentResponse track(String code){return repo.findByTrackingNumber(code).map(ShipmentResponse::from).orElseThrow(()->new ShipmentNotFoundException("Tracking number not found: "+code));}
 @Transactional public ShipmentResponse status(long id,ShipmentStatus next){
  Shipment s=repo.findById(id).orElseThrow(()->new ShipmentNotFoundException("Shipment not found: "+id));
  if(!allowed(s.getStatus(),next))throw new IllegalStateException("Invalid status transition: "+s.getStatus()+" -> "+next);
  s.setStatus(next); return ShipmentResponse.from(repo.save(s));
 }
 @Transactional public void cancel(long id){Shipment s=repo.findById(id).orElseThrow(()->new ShipmentNotFoundException("Shipment not found: "+id));
  if(s.getStatus()==ShipmentStatus.DELIVERED)throw new IllegalStateException("Delivered shipment cannot be cancelled"); s.setStatus(ShipmentStatus.CANCELLED);repo.save(s);}
 private boolean allowed(ShipmentStatus a,ShipmentStatus b){if(a==b)return true;return switch(a){
  case BOOKED -> b==ShipmentStatus.PICKED_UP||b==ShipmentStatus.CANCELLED;
  case PICKED_UP -> b==ShipmentStatus.IN_TRANSIT||b==ShipmentStatus.CANCELLED;
  case IN_TRANSIT -> b==ShipmentStatus.OUT_FOR_DELIVERY||b==ShipmentStatus.CANCELLED;
  case OUT_FOR_DELIVERY -> b==ShipmentStatus.DELIVERED;
  case DELIVERED,CANCELLED -> false;};}
}