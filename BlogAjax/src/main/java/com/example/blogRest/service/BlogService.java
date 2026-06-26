package com.example.blogRest.service;

import com.example.blogRest.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    List<Blog> findAll();

    List<Blog> findByCategoryId(Long categoryId);

    Page<Blog> findPage(String keyword, Pageable pageable);

    Optional<Blog> findById(Long id);
}
