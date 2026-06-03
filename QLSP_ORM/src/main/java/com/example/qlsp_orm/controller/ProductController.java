package com.example.qlsp_orm.controller;

import com.example.qlsp_orm.model.Product;
import com.example.qlsp_orm.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/products";
    }
    @GetMapping("/products")
    public String listProducts(@RequestParam(required = false) String keyword, Model model){
        model.addAttribute("products", productService.searchByName(keyword));
        model.addAttribute("keyword", keyword);
        return "products/list";
    }
    @GetMapping("/products/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle", "Thêm sản phẩm mới");
        return "products/form";
    }

    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);

        if (product == null) {
            return "redirect:/products";
        }

        model.addAttribute("product", product);
        return "products/detail";
    }

    @GetMapping("/products/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);

        if (product == null) {
            return "redirect:/products";
        }

        model.addAttribute("product", product);
        model.addAttribute("pageTitle", "Cập nhật sản phẩm");
        return "products/form";
    }

    @GetMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}
