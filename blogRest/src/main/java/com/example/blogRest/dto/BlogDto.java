package com.example.blogRest.dto;

import com.example.blogRest.model.Blog;

import java.time.LocalDateTime;

public record BlogDto(
        Long id,
        String title,
        String summary,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        CategoryDto category
) {
    public static BlogDto from(Blog blog) {
        return new BlogDto(
                blog.getId(),
                blog.getTitle(),
                blog.getSummary(),
                blog.getContent(),
                blog.getCreatedAt(),
                blog.getUpdatedAt(),
                CategoryDto.from(blog.getCategory())
        );
    }
}
