package com.example.qlsp_orm.service;

import com.example.qlsp_orm.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    void save(Product product);
    void deleteById(Long id);
    List<Product> searchByName(String keyword);
}
