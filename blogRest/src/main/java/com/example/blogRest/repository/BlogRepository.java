package com.example.blogRest.repository;

import com.example.blogRest.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByOrderByCreatedAtDesc();

    List<Blog> findByCategoryIdOrderByCreatedAtDesc(Long categoryId);
}
