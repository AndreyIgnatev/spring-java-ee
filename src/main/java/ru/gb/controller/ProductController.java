package ru.gb.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.model.Product;
import ru.gb.service.ProductService;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;



    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String processForm(Product product, Model model) {
        model.addAttribute("productList", productService.save(product));
        return "product-list";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getProductId(Model model, @PathVariable Integer id) {
        model.addAttribute("productId", productService.findById(id));
        return "productId";
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public String getAllProducts(Model model) {
        model.addAttribute("productList", productService.findAll());
        return "product-list";
    }

//    @RequestMapping(path = "/del/{id}", method = RequestMethod.GET)
//    public String getAllProducts(@PathVariable Integer id) {
//        productService.deleteById(id);
//        return "product-list";
//    }
}
