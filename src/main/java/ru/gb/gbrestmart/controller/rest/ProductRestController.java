package ru.gb.gbrestmart.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbrestmart.controller.dto.ProductDto;
import ru.gb.gbrestmart.service.ProductGateway;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductRestController {

    private final ProductGateway productGateway;

    @GetMapping("/all")
    public List<ProductDto> getProductList() {
        return productGateway.findProductAll();
    }

}
