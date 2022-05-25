package ru.gb.gbrestmart.controller.rest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbrestmart.controller.dto.CartDto;
import ru.gb.gbrestmart.controller.dto.ProductDto;
import ru.gb.gbrestmart.entity.Cart;
import ru.gb.gbrestmart.entity.Product;
import ru.gb.gbrestmart.service.CartService;
import ru.gb.gbrestmart.service.ProductService;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartRestController {

    private final CartService cartService;

    @GetMapping
    public List<Cart> getProductList() {
        return cartService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        Cart cart;
        if (id != null) {
            cart = cartService.findById(id);
            if (cart != null) {
                return new ResponseEntity<>(cart, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> handlePost(@Validated @RequestBody CartDto cartDto) {
        CartDto savedCartDto = cartService.save(cartDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/api/v1/cart/" + savedCartDto.getId()));
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }
//http://localhost:8080/api/v1/cart/addProductInCart?id_cart=5&id_product=2
//  1. Добавление товара в корзину.
    @GetMapping("/addProductInCart")
    public ResponseEntity<?> addProductCart(@RequestParam Long id_cart, @RequestParam Long id_product) {
        Cart cart = cartService.addProductInCart(id_cart,id_product);
        if(cart != null){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create("/api/v1/cart/"+ cart.getId()));
            return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
        }else {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create("/api/v1/cart/" + " NO ID CART! "));
            return new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    /*
    Для проверки данных используются аннотации над полями класса. Это декларативный подход, который не загрязняет код.
    При передаче размеченного таким образом объекта класса в валидатор, происходит проверка на ограничения (@Validated).
    */

    /*
    @RequestBody, Spring на лету попытается преобразовать содержимое тела входящего запроса
    в ваш объект параметра CartDto cartDto.
     */
    @PutMapping("/{cartId}")
    public ResponseEntity<?> handleUpdate(@PathVariable("cartId") Long id, @Validated @RequestBody CartDto cartDto) {
        cartDto.setId(id);
        CartDto savedCartDto = cartService.save(cartDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/api/v1/cart/" + savedCartDto.getId()));
        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{cartId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("cartId") Long id) {
        cartService.deleteById(id);
    }

    // 2. Отображения всех товаров в корзине.
    @GetMapping("/productInCartId/{id_cart}")
    public CartDto productInCartId(@PathVariable Long id_cart) {
        return cartService.productInCartId(id_cart);
    }

    // 3. Товар должен быть удален из базы (удаляется связка между Cart и Product).
    @GetMapping("/delProductInCartId")
    public CartDto delProductInCartId(@RequestParam Long id_cart, @RequestParam Long id_product) {
        return cartService.delProductInCartId(id_cart,id_product);
    }

}
