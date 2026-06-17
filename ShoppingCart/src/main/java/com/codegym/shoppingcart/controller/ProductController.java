package com.codegym.shoppingcart.controller;

import com.codegym.shoppingcart.repository.ProductRepository;
import com.codegym.shoppingcart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    @GetMapping("/")
    public String home(){
        return "redirect:/products";
    }
    @GetMapping("/products")
    public String showProductList(Model model){
        model.addAttribute("products", productService.findAll());
        return "product/list";
    }
    @GetMapping("/products/{id}")
    public String showProductDetail(@PathVariable Long id, Model model){
        model.addAttribute("product", productRepository.findById(id).orElseThrow());
        return "product/detail";
    }
}
