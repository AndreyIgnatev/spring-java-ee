package serverdz10order.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_client")
//@NamedQueries({
//        @NamedQuery(name = "Manufacturer.findNameById",
//        query = "select m.name from Order m where m.id = :id"),
//        @NamedQuery(name = "Manufacturer.findById",
//        query = "select m from Order m where m.id = :id")
//})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "buyer_details")
    private String buyer_details;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> orderList;

    public void addProduct(Product product) {
        if (orderList.isEmpty()) {
            orderList = new ArrayList<>();
        }
        orderList.add(product);
    }
}

