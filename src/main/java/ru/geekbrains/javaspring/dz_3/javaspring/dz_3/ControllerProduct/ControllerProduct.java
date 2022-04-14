package ru.geekbrains.javaspring.dz_3.javaspring.dz_3.ControllerProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.javaspring.dz_3.javaspring.dz_3.ServiceProduct.ProducktService;
import ru.geekbrains.javaspring.dz_3.javaspring.dz_3.model.Product;

@Controller
public class ControllerProduct {
    ProducktService producktService;
    @Autowired
    public ControllerProduct(ProducktService producktService){
        this.producktService = producktService;
    }


    @GetMapping("/")
    public String indexProductPage(Model model) {
        model.addAttribute("productAll",producktService.getListProducts());
        return "productsList";
    }

    @GetMapping("/create")
    public String showCreateForm(){
        return "productForm";
    }
    @PostMapping("/create")
    public String saveStudent(@RequestParam int id, @RequestParam String title, @RequestParam int cost) {
        Product product= new Product(id, title, cost);
        producktService.addProductList(product);
        return "redirect:/";
    }


}
