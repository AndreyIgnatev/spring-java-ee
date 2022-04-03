package ru.gb.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.model.Product;
import ru.gb.service.ProductService;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
@Slf4j

public class ProductController {
@Autowired
    private final ProductService productService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String processForm(Product product) {
        if (product.getId() == null) {
            productService.oneProduct(product.getId());
        }
        return "redirect:/message/ProductOne";
    }


    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public String getAllMessages(Model model) {
        model.addAttribute("product", productService.allProduct());
        return "product-all";
    }

}
