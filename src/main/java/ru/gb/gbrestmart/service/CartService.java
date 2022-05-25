package ru.gb.gbrestmart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.gbrestmart.controller.dto.CartDto;
import ru.gb.gbrestmart.controller.dto.ProductDto;
import ru.gb.gbrestmart.dao.CartDao;
import ru.gb.gbrestmart.entity.Cart;
import ru.gb.gbrestmart.entity.Product;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartDao cartDao;
    private final ProductService productService;

    // Перезаписываем БД существующую строку или создаем новую.
    public CartDto save(CartDto cartDto) {
        Cart saveCart;
        if (cartDto.getId() != null) {
            Optional<Cart> cartFromDBOptional = cartDao.findById(cartDto.getId());
            saveCart = cartFromDBOptional.orElseGet(Cart::new);
        } else {
            saveCart = new Cart();
        }
        saveCart.setStatus(cartDto.getStatus());
        // Тут в БД получится пустая строка с новым id, если id не найден или не существует.
        saveCart = cartDao.save(saveCart);
        cartDto.setId(saveCart.getId());
        return cartDto;
    }

    public Cart addProductInCart(Long id_cart, Long id_product) {
        Cart cart = findById(id_cart);
        Product product = productService.findById(id_product);
        if (cart != null & product != null) {
            cart.addProduct(product);
            return cartDao.save(cart);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public Cart findById(Long id) {
        return cartDao.findById(id).orElse(null); // Если Optional<Cart> - пустой, вернется по заданному дефолту null
    }

    public List<Cart> findAll() {
        return cartDao.findAll();
    }

    public void deleteById(Long id) {
        try {
            cartDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
        }
    }

    public CartDto productInCartId(Long id_cart) {
        Cart cart=null;
        String[] arr=null;
        List<Cart> cartList = cartDao.findAll().stream().filter((Cart c) -> {return c.getId() == id_cart;}).collect(Collectors.toList());
        if (cartList.size() == 1){
            cart = cartList.get(0);
        }
        if (cart != null){
            arr = new String[cart.getProducts().size()];
            for (int i = 0; i < cart.getProducts().size(); i++) {
                arr[i] = cart.getProducts().get(i).getTitle();
            }
        }
        return new CartDto(cart.getId(), cart.getStatus(), arr);
    }

    public CartDto delProductInCartId(@RequestParam Long id_cart, @RequestParam Long id_product) {
        Cart cart = cartDao.findAll().get(Math.toIntExact(id_cart));
        for (int i = 0; i < cart.getProducts().size(); i++){
            if (cart.getProducts().get(i).getId() == id_product) {
                cart.getProducts().remove(i);
            }
            cartDao.save(cart);
        }
        return new CartDto(cart.getId(), cart.getStatus(),cart.getProducts().toArray(new String[cart.getProducts().size()]));
    }
}
