package com.example.blogRest.dto;

import com.example.blogRest.model.Category;

public record CategoryDto(Long id, String name) {
    public static CategoryDto from(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}
