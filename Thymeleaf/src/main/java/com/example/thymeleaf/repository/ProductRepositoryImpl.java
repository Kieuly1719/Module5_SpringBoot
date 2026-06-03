package com.example.thymeleaf.repository;

import com.example.thymeleaf.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class ProductRepositoryImpl implements ProductRepository{
    private final List<Product> products = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    @PostConstruct
    public void initData() {
        products.add(new Product(
                idCounter.getAndIncrement(),
                "Laptop Dell",
                15000000.0,
                "Laptop phục vụ học tập và làm việc",
                "Dell"
        ));

        products.add(new Product(
                idCounter.getAndIncrement(),
                "iPhone 15",
                22000000.0,
                "Điện thoại thông minh",
                "Apple"
        ));

        products.add(new Product(
                idCounter.getAndIncrement(),
                "Tai nghe Sony",
                2500000.0,
                "Tai nghe chống ồn",
                "Sony"
        ));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            product.setId(idCounter.getAndIncrement());
            products.add(product);
        } else {
            Product existingProduct = findById(product.getId());

            if (existingProduct != null) {
                existingProduct.setName(product.getName());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setDescription(product.getDescription());
                existingProduct.setManufacturer(product.getManufacturer());
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    @Override
    public List<Product> searchByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return products;
        }

        String lowerKeyword = keyword.toLowerCase();

        return products.stream()
                .filter(product -> product.getName().toLowerCase().contains(lowerKeyword))
                .toList();
    }
}
