package com.example.blogRest.service;

import com.example.blogRest.model.Blog;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    List<Blog> findAll();

    List<Blog> findByCategoryId(Long categoryId);

    Optional<Blog> findById(Long id);
}
