package ru.gb.thymeleafprepare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.thymeleafprepare.entity.Cart;
import ru.gb.thymeleafprepare.entity.Cart_Product;
import ru.gb.thymeleafprepare.entity.Product;
import ru.gb.thymeleafprepare.service.CartService;
import ru.gb.thymeleafprepare.service.ProductService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping("/all")
    public String getProductList(Model model) {
        model.addAttribute("cartlist", cartService.findAll());
        return "cart-list";
    }

    @GetMapping("/product_in_cart")
    public String showFormCartPro(Model model) {
        Cart_Product cartPro = new Cart_Product();
        model.addAttribute("cartPro", cartPro);
        return "cartpro-form";
    }

    @PostMapping("/product_in_cart")
    public String saveCartProduct(Cart_Product cartProduct) {
        List<Product> productList = productService.findAll();
        List<Cart> cartList = cartService.findAll();
        cartList.get(Math.toIntExact(cartProduct.getId_cart())).addProduct(productList.get(Math.toIntExact(cartProduct.getId_product())));
        return "cart-product-list";
    }

    @GetMapping("/cart-product-list")
    public String getProductListCart(Model model) {
        model.addAttribute("cartprolist", cartService.findAllCart());
        return "cart-product-list";
    }

    @PostMapping
    public String saveCart(Cart cart) {
        cartService.save(cart);
        return "redirect:/cart/all";
    }

    @GetMapping
    public String showFormCart(Model model) {
        Cart cart = new Cart();
        model.addAttribute("cartmodel", cart);
        return "cart-form";
    }
}
