package ru.gb.repository;

import lombok.*;
import org.springframework.stereotype.Component;
import ru.gb.model.Product;

import javax.annotation.PostConstruct;
import java.util.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProductRepository {

    List<Product> productList = new ArrayList<>();

    private int count;

   @PostConstruct
   void init(){
       productList.add(new Product(1,"moloko",10));
       productList.add(new Product(2,"water",11));
       productList.add(new Product(3,"koffe",12));
       productList.add(new Product(4,"tea",13));
   }

}

