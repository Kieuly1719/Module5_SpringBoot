package com.example.blogRest.controller;

import com.example.blogRest.dto.BlogDto;
import com.example.blogRest.dto.CategoryDto;
import com.example.blogRest.service.BlogService;
import com.example.blogRest.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogRestController {
    private final BlogService blogService;
    private final CategoryService categoryService;

    public BlogRestController(BlogService blogService, CategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<CategoryDto> getCategories() {
        return categoryService.findAll()
                .stream()
                .map(CategoryDto::from)
                .toList();
    }

    @GetMapping("/blogs")
    public List<BlogDto> getBlogs() {
        return blogService.findAll()
                .stream()
                .map(BlogDto::from)
                .toList();
    }

    @GetMapping("/categories/{categoryId}/blogs")
    public ResponseEntity<List<BlogDto>> getBlogsByCategory(@PathVariable Long categoryId) {
        if (categoryService.findById(categoryId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<BlogDto> blogs = blogService.findByCategoryId(categoryId)
                .stream()
                .map(BlogDto::from)
                .toList();
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<BlogDto> getBlogDetail(@PathVariable Long id) {
        return blogService.findById(id)
                .map(BlogDto::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
