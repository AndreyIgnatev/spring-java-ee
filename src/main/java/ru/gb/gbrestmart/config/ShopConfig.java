package ru.gb.gbrestmart.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

import ru.gb.gbrestmart.service.ProductGateway;


@Configuration
@EnableFeignClients(basePackageClasses = ProductGateway.class)
public class ShopConfig {

}
