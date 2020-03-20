package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Product;
import com.codeup.springblog.repositories.ProductRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private ProductRepo pdao;

    public ProductController(ProductRepo pdao) {
        this.pdao = pdao;
    }

    @GetMapping("/products")
    @ResponseBody
    public List<Product> products() {
        return pdao.findAll();
    }


    // without form model binding
//    @GetMapping("/products/create")
//    public String create() {
//        return "products/create";
//    }
//
//    @PostMapping("/products")
//    public String insert(
//            @RequestParam String name,
//            @RequestParam int priceInCents
//    ) {
//        pdao.save(
//            new Product(
//                name,
//                priceInCents
//            )
//        );
//        return "redirect:/products";
//    }

    // with form model binding

//        @GetMapping("/products/create")
//        public String create(Model model) {
//            model.addAttribute("product", new Product());
//            return "products/create-m";
//        }
//
//        @PostMapping("/products")
//        public String insert(@ModelAttribute Product product) {
//            pdao.save(product);
//            return "redirect:/products";
//        }

}
