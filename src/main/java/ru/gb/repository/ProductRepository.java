package ru.gb.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
@Transactional
public class ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private int count;

    public List<Product> save(Product product) {
        product.setId(count++);
        productList.add(product);
        return new ArrayList<Product>(productList);
    }


        public List<Product> findAll() {
            return entityManager.createQuery("select m from Product m").getResultList();
        }


    public Product findId(Integer id) {
        Product productId = null;
        for (int i = 0; i < productList.size(); i++) {

            if( id == productList.get(i).getId()){
                productId=productList.get(i);
            }
        }
        return productId;
    }

    public void deleteById(Integer id) {
        for (int i = 0; i < productList.size(); i++) {
            if (id == productList.get(i).getId()) {
                productList.remove(i);
            }
        }
    }
}
