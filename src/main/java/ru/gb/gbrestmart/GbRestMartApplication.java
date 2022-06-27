package ru.gb.gbrestmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class GbRestMartApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GbRestMartApplication.class, args);
//        context.getBean(ProductService.class).init();
    }

}
