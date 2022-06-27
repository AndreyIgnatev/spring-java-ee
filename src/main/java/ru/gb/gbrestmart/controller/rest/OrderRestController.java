package ru.gb.gbrestmart.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbrestmart.controller.dto.OrderDto;
import ru.gb.gbrestmart.service.OrderGateway;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderRestController {

    private final OrderGateway orderGateway;

    @PostMapping("/create")
    public ResponseEntity<String> getProductList(@Validated @RequestBody OrderDto orderDto) {
        return orderGateway.createOrder(orderDto);
    }

    @GetMapping("/deleted/{id}")
    public ResponseEntity<String> deletedOrder(@PathVariable Long id) {
        return orderGateway.deletedOrderId(id);
    }

    @GetMapping ("/addProductIdInOrderId")
    public ResponseEntity<String> addProductOrder(@RequestParam Long productId, @RequestParam Long orderId) {
        return orderGateway.addProductInOrderId(productId,orderId);
    }

    @GetMapping ("/delProductIdInOrderId")
    public ResponseEntity<String> delProductOrder(@RequestParam Long productId, @RequestParam Long orderId) {
        return orderGateway.delProductInOrderId(productId,orderId);
    }
}
