package com.geekbrains.hibernate;
import com.geekbrains.hibernate.config.HibernateConfig;
import com.geekbrains.hibernate.dao.ProductDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ShopApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        ProductDao productDao =context.getBean(ProductDao.class);
        //System.out.println(productDao.findAll());
        System.out.println(productDao.findNameById(2L));
    }
}
