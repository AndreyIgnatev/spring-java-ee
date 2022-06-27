package serverdz10order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import serverdz10order.controller.dto.ProductDto;
import serverdz10order.dao.ProductDao;
import serverdz10order.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
private final ProductDao productDao;

public List<ProductDto> findProductAll(){
    List<Product> productList = productDao.findAll();
    List<ProductDto> productDtoList = new ArrayList<>();
    System.out.println(productList.size());
    for (int i = 0; i < productList.size(); i++){
        productDtoList.add(i,new ProductDto (productList.get(i).getId(),productList.get(i).getName(),productList.get(i).getCost()));
    }
    return productDtoList;
}
}
