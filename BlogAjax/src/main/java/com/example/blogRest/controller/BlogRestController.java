package com.example.blogRest.controller;

import com.example.blogRest.dto.BlogDto;
import com.example.blogRest.dto.CategoryDto;
import com.example.blogRest.service.BlogService;
import com.example.blogRest.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogRestController {
    private static final int DEFAULT_PAGE_SIZE = 20;

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
    public BlogPageResponse getBlogs(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        int safePage = Math.max(page, 0);
        int safeSize = size <= 0 ? DEFAULT_PAGE_SIZE : Math.min(size, DEFAULT_PAGE_SIZE);
        Page<BlogDto> blogPage = blogService.findPage(keyword, PageRequest.of(safePage, safeSize))
                .map(BlogDto::from);

        return BlogPageResponse.from(blogPage);
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

    public record BlogPageResponse(
            List<BlogDto> content,
            int page,
            int size,
            long totalElements,
            int totalPages,
            boolean first,
            boolean last
    ) {
        public static BlogPageResponse from(Page<BlogDto> page) {
            return new BlogPageResponse(
                    page.getContent(),
                    page.getNumber(),
                    page.getSize(),
                    page.getTotalElements(),
                    page.getTotalPages(),
                    page.isFirst(),
                    page.isLast()
            );
        }
    }
}
