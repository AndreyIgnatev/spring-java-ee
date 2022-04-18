package com.geekbrains.hibernate.dao;

import com.geekbrains.hibernate.entity.Product;

public interface ProductDao {
    Iterable<Product> findAll();
    String findNameById(Long id);
    Product findById(Long id);
    void insert(Product product);
    void update(Product product);
    int deleteById(Long id);
}
