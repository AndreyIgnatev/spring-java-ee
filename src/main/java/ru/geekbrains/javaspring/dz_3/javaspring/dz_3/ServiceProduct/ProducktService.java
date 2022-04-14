package ru.geekbrains.javaspring.dz_3.javaspring.dz_3.ServiceProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.javaspring.dz_3.javaspring.dz_3.RepoProduct.RepositoriesProduct;
import ru.geekbrains.javaspring.dz_3.javaspring.dz_3.model.Product;

import java.util.List;

@Service
public class ProducktService {
    RepositoriesProduct repositoryProduct;
    @Autowired
    public ProducktService(RepositoriesProduct repositoryProduct){
        this.repositoryProduct = repositoryProduct;
    }

    public List<Product> getListProducts(){
        return repositoryProduct.productListAll();
    }

    public void addProductList(Product product){
        repositoryProduct.productListAddProduckt(product);

    }

}
