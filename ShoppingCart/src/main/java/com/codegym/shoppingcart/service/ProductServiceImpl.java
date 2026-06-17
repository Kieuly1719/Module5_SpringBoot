package com.codegym.shoppingcart.service;

import com.codegym.shoppingcart.model.Product;
import com.codegym.shoppingcart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    @Override
    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }
    @Override
    public Product save(Product product){
        return productRepository.save(product);
    }
}
