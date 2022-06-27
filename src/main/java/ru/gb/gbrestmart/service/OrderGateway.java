package ru.gb.gbrestmart.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbrestmart.controller.dto.OrderDto;


@FeignClient(url = "localhost:45732/order", value = "orderGateway")
public interface OrderGateway {
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> createOrder(OrderDto orderDto);

    @GetMapping(value = "deleted/{id}", consumes = MediaType.ALL_VALUE,produces = MediaType.ALL_VALUE)
    ResponseEntity<String> deletedOrderId(@PathVariable Long id);

    @GetMapping(value = "addProductOrder", consumes = MediaType.ALL_VALUE,produces = MediaType.ALL_VALUE)
    ResponseEntity<String> addProductInOrderId(@RequestParam Long productId, @RequestParam Long orderId);

    @GetMapping(value = "deletedProductOrder", consumes = MediaType.ALL_VALUE,produces = MediaType.ALL_VALUE)
    ResponseEntity<String> delProductInOrderId(@RequestParam Long productId, @RequestParam Long orderId);

}
