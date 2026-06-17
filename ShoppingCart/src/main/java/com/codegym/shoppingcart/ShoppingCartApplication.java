package com.codegym.shoppingcart;

import com.codegym.shoppingcart.model.Product;
import com.codegym.shoppingcart.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShoppingCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(ProductService productService) {
        return args -> {
            if (productService.findAll().isEmpty()) {
                productService.save(Product.builder()
                        .code("4564")
                        .name("Sản phẩm 1")
                        .oldPrice(400000)
                        .newPrice(350000)
                        .image("/images/product1.jpg")
                        .description("Giỏ hoa trắng sang trọng, phù hợp tặng sinh nhật, khai trương.")
                        .build());

                productService.save(Product.builder()
                        .code("3199")
                        .name("Sản phẩm 2")
                        .oldPrice(250000)
                        .newPrice(200000)
                        .image("/images/product2.jpg")
                        .description("Bó hoa hồng nhẹ nhàng, phù hợp tặng người thân.")
                        .build());

                productService.save(Product.builder()
                        .code("5001")
                        .name("Sản phẩm 3")
                        .oldPrice(550000)
                        .newPrice(500000)
                        .image("/images/product3.jpg")
                        .description("Giỏ hoa phối màu tinh tế.")
                        .build());

                productService.save(Product.builder()
                        .code("6001")
                        .name("Sản phẩm 4")
                        .oldPrice(650000)
                        .newPrice(600000)
                        .image("/images/product4.jpg")
                        .description("Giỏ hoa hướng dương nổi bật.")
                        .build());
            }
        };
    }
}