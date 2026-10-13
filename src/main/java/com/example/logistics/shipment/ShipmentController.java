package com.example.logistics.shipment;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/shipments")
public class ShipmentController {
 private final ShipmentService service; public ShipmentController(ShipmentService service){this.service=service;}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public ShipmentResponse book(@Valid @RequestBody ShipmentRequest r){return service.book(r);}
 @GetMapping public Page<ShipmentResponse> list(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="20") int size){return service.list(page,size);}
 @GetMapping("/{id}") public ShipmentResponse get(@PathVariable long id){return service.get(id);}
 @GetMapping("/track/{trackingNumber}") public ShipmentResponse track(@PathVariable String trackingNumber){return service.track(trackingNumber);}
 @PatchMapping("/{id}/status") public ShipmentResponse status(@PathVariable long id,@Valid @RequestBody StatusUpdateRequest r){return service.status(id,r.status());}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void cancel(@PathVariable long id){service.cancel(id);}
}