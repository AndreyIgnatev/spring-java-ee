package serverdz10order.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import serverdz10order.controller.dto.OrderDto;
import serverdz10order.dao.OrderDao;
import serverdz10order.dao.ProductDao;
import serverdz10order.entity.Order;
import serverdz10order.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderDao orderDao;
    private final ProductDao productDao;

    public List<OrderDto> findOrderAllAndProduct() {
        List<Order> orderList = orderDao.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
        System.out.println(orderList.size());
        for (int i = 0; i < orderList.size(); i++) {
            orderDtoList.add(i, new OrderDto(
                    orderList.get(i).getId(),
                    orderList.get(i).getBuyer_details(),
                    orderList.get(i).getOrderList().toArray(new String[orderList.get(i).getOrderList().size()])));
        }
        return orderDtoList;
    }

    public Order createOrder(OrderDto orderDto) {
        return orderDao.save(Order.builder().buyer_details(orderDto.getBuyer_details()).build());
    }

    public void deleteById(Long id) {
        orderDao.deleteById(id);
    }

    public Optional<Order> findById(Long id) {
        return orderDao.findById(id);
    }

    public ResponseEntity<String> addProductInOrder(Long idProduct, Long idOrder) {
        Optional<Product> product = Optional.ofNullable(productDao.getById(idProduct));
        Optional<Order> order = Optional.ofNullable(orderDao.getById(idOrder));
        if (product.isPresent() && order.isPresent()) {
            Product product1 = product.get();
            Order order1 = order.get();
            order1.addProduct(product1);
            orderDao.save(order1);
            return new ResponseEntity<>("Продукт в корзину добавлен!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Продукт в корзину НЕ добавлен!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> delProductInOrderId(Long idProduct, Long idOrder) {
        Optional<Product> product = Optional.ofNullable(productDao.getById(idProduct));
        Optional<Order> order = Optional.ofNullable(orderDao.getById(idOrder));
        if (product.isPresent() && order.isPresent()) {
            Order order1 = order.get();
            List<Order> orderListDel;
            for (int i = 0; i < order1.getOrderList().size(); i++) {
                if (order1.getOrderList().get(i).getId() == idProduct) {
                    order1.getOrderList().remove(i);
                }
            }
            orderDao.save(order1);
            return new ResponseEntity<>("Продукт из корзины удален!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Продукт в корзине НЕ найден!", HttpStatus.NOT_FOUND);
    }
}
