package com.example.logistics.common;
import com.example.logistics.shipment.ShipmentNotFoundException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.stream.Collectors;
@RestControllerAdvice public class ApiExceptionHandler {
 @ExceptionHandler(ShipmentNotFoundException.class) ResponseEntity<Map<String,String>> missing(RuntimeException e){return ResponseEntity.status(404).body(Map.of("error",e.getMessage()));}
 @ExceptionHandler({IllegalStateException.class}) ResponseEntity<Map<String,String>> conflict(RuntimeException e){return ResponseEntity.status(409).body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<Map<String,String>> bad(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Map<String,String>> invalid(MethodArgumentNotValidException e){
  String m=e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).collect(Collectors.joining("; "));
  return ResponseEntity.badRequest().body(Map.of("error",m));}
}