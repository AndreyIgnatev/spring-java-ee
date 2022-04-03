package ru.gb.service;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ru.gb.model.Product;
import ru.gb.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Setter
@Getter
@Builder
public class ProductService {
@Autowired
    private final ProductRepository productRepository;

    public Product oneProduct( int id ){
        for (Product p: productRepository.getProductList()) {
            if (id == p.getId()){
                return p;
            }
        }
        System.out.println("Товар НЕ найден ошибка!");
        return null;
    }

    public List<Product> allProduct(){
        return productRepository.getProductList();
    }

}
