package ru.geekbrains.spring.dz_2;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {

    private List<Product> productList;


    @PostConstruct
    private void init(){
        productList = new ArrayList<>();
        productList.add(new Product(1,"milk",10));
        productList.add(new Product(2,"coffee",11));
        productList.add(new Product(3,"tea",12));
        productList.add(new Product(4,"cacao",13));
        productList.add(new Product(5,"water",14));
    }

    Product oneProduct( int id ){
        for (Product p: productList) {
            if (id == p.getId()){
                return p;
            }
        }
        System.out.println("Товар НЕ найден ошибка!");
        return null;
    }

    List<Product> allProduct(){
        return productList;
    }
}
