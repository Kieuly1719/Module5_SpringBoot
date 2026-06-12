package com.example.blog.service;

import com.example.blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    void save(Blog blog);
    Page<Blog> findAll(Pageable pageable);
    Page<Blog> searchByTitle(String keyword, Pageable pageable);
    Page<Blog> findByCategory(Long categoryId, Pageable pageable);
    Page<Blog> searchByTitleAndCategory(String keyword, Long categoryId, Pageable pageable);
    Blog findById(Long id);
    void delete(Long id);
    boolean existsByCategoryId(Long categoryId);
}
