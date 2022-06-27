package ru.gb.gbrestmart.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.gbrestmart.controller.dto.ProductDto;

import java.util.List;


@FeignClient(url = "localhost:45732/product", value = "productGateway")
public interface ProductGateway {
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    List<ProductDto> findProductAll();
}
