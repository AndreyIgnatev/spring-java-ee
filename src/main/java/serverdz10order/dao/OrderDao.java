package serverdz10order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import serverdz10order.entity.Order;
import serverdz10order.entity.Product;


public interface OrderDao extends JpaRepository<Order, Long> {
//    Optional<Product> findByTitle(String title);
//    List<Product> findAllByTitleContaining(String title);
}
