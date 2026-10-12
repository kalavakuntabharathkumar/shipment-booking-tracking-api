package com.example.logistics.shipment;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
class PricingServiceTest {
 @Test void computesRate(){assertEquals(new BigDecimal("112.00"),new PricingService().calculate(new BigDecimal("2.000")));}
}