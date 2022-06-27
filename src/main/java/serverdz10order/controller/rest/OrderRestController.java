package serverdz10order.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import serverdz10order.controller.dto.OrderDto;
import serverdz10order.entity.Order;
import serverdz10order.service.OrderService;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping
    public List<OrderDto> getProductList() {
        return orderService.findOrderAllAndProduct();
    }

    @PostMapping("/create")
    public ResponseEntity<String> getProductList(@Validated @RequestBody OrderDto orderDto) {
        Order order = orderService.createOrder(orderDto);
        return new ResponseEntity<>("Корзина создана.", HttpStatus.CREATED);
    }

    @GetMapping("deleted/{id}")
    public ResponseEntity<String> deletedOrder(@PathVariable Long id) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()) {
            orderService.deleteById(id);
            return new ResponseEntity<>("Объект c id: " + id + " удален!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Объект не найден!", HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/api/v1/order/addProductOrder?productId=2&orderId=2
    @GetMapping("/addProductOrder")
    ResponseEntity<String> addProductInOrderId(@RequestParam Long productId, @RequestParam Long orderId) {
        return orderService.addProductInOrder(productId, orderId);
    }

    //http://localhost:8080/api/v1/order/delProductIdInOrderId?productId=2&orderId=2
    @GetMapping("/deletedProductOrder")
    ResponseEntity<String> deletedProductInOrderId(@RequestParam Long productId, @RequestParam Long orderId) {
        return orderService.delProductInOrderId(productId, orderId);
    }
}
