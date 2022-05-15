package com.geekbrains.webapp.repositories;

import com.geekbrains.webapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
     @Query(value = "delete from Product a where a.id = :id")
     void delByIdRepo(@Param("id") Long product);
}
