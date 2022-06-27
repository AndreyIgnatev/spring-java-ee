package serverdz10order.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import serverdz10order.controller.dto.ProductDto;
import serverdz10order.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductRestController {

    private final ProductService productService;

    @PostMapping
    public List<ProductDto> getProductList() {
        return productService.findProductAll();
    }

//    @GetMapping("/info")
//    public List<ProductManufacturerDto> getFullInfoProductList() {
//        return orderService.findFullInfo();
//    }
//
//    @GetMapping("/{productId}")
//    public ResponseEntity<?> getProduct(@PathVariable("productId") Long id) {
//        ProductDto productDto;
//        if (id != null) {
//            productDto = orderService.findById(id);
//            if (productDto != null) {
//                return new ResponseEntity<>(productDto, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> handlePost(@Validated @RequestBody ProductDto productDto) {
//        ProductDto savedProductDto = orderService.save(productDto);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(URI.create("/api/v1/product/" + savedProductDto.getId()));
//        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{productId}")
//    public ResponseEntity<?> handleUpdate(@PathVariable("productId") Long id, @Validated @RequestBody ProductDto productDto) {
//        productDto.setId(id);
//        ProductDto savedProductDto = orderService.save(productDto);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(URI.create("/api/v1/product/" + savedProductDto.getId()));
//        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
//    }
//
//    @DeleteMapping("/{productId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteById(@PathVariable("productId") Long id) {
//        orderService.deleteById(id);
//    }
}
