package com.geekbrains.webapp.controllers;

import com.geekbrains.webapp.dtos.ProductDto;
import com.geekbrains.webapp.model.Product;
import com.geekbrains.webapp.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Над каждым методом автоматом будет стоять @ResponseBody, то есть вместо страниц возвращаются JSON
 *
 * */
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private Long id;

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).get());
    }

    @PostMapping("/products")
    public ProductDto save(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        productService.save(product);
        return new ProductDto(product);
    }

    @GetMapping("/products/del/{id}")
    public void deletedById(@PathVariable Long id) {
        productService.delById(id);
    }

}
