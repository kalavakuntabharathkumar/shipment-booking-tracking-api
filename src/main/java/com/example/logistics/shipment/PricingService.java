package com.example.logistics.shipment;
import org.springframework.stereotype.Service;
import java.math.*;
@Service
public class PricingService {
 public BigDecimal calculate(BigDecimal kg){return new BigDecimal("75.00").add(kg.multiply(new BigDecimal("18.50"))).setScale(2,RoundingMode.HALF_UP);}
}