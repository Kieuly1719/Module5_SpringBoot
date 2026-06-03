package com.example.thymeleaf.repository;

import com.example.thymeleaf.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    Product findById(Long id);

    void save(Product product);

    void deleteById(Long id);

    List<Product> searchByName(String keyword);
}
