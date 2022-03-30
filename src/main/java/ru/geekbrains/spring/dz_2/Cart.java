package ru.geekbrains.spring.dz_2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
// @Scope(value = "prototype")
public class Cart {
    private List<Product> productCartList;
    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    void init() {
        productCartList = new ArrayList<>();
    }

    void saveProduct(int id) {
        productCartList.add(productRepository.oneProduct(id));
        System.out.println("Общее число товаров в корзине: "+ productCartList.size());
    }

    void delProduct(int id) {
        for (int i = 0; i < productCartList.size(); i++) {
            if (productCartList.get(i).getId() == id) {
                productCartList.remove(i);
                System.out.println("Товар найден и удален из корзины!");
                System.out.println("Общее число товаров в корзине: "+ productCartList.size());
                break;
            }
        }
    }

    void allProductRepo() {
        for (Product p : productRepository.allProduct()) {
            System.out.println(p);
        }
    }
}
