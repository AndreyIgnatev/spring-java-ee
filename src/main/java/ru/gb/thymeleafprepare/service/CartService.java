package ru.gb.thymeleafprepare.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.thymeleafprepare.dao.CartDao;
import ru.gb.thymeleafprepare.entity.Cart;
import ru.gb.thymeleafprepare.entity.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartDao cartDao;


    public Cart save(Cart cart) {
        return cartDao.save(cart);
    }

    public List<Cart> findAll() {
        return cartDao.findAll();
    }

    public List<Product> findAllCart() {
        return findAllCart();
    }
}
