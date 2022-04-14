package ru.geekbrains.javaspring.dz_3.javaspring.dz_3.RepoProduct;

import org.springframework.stereotype.Component;
import ru.geekbrains.javaspring.dz_3.javaspring.dz_3.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
@Component
public class RepositoriesProduct {
    List<Product> productList;

    @PostConstruct
    void init(){
        productList = new ArrayList<>();
        productList.add(new Product(1,"moloko",10));
        productList.add(new Product(2,"kefir",11));
        productList.add(new Product(3,"cola",12));
        productList.add(new Product(4,"esentuki",13));

    }

    public void productListAddProduckt(Product product){
        productList.add(product);
    }

    public List<Product> productListAll(){
        return productList;
    }

    public Product productListOne(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(id).getId() == id) {
                return productList.get(id);
            }
        }
        return new Product(-1,"null",0);
    }
}
