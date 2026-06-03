package com.example.blog.service;

import com.example.blog.model.Blog;

import java.util.List;

public interface BlogService {
    void save(Blog blog);
    List<Blog> findAll();
    Blog findById(Long id);
    void delete(Long id);
}
