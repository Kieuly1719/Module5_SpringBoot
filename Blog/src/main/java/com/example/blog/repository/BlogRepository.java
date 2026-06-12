package com.example.blog.repository;

import com.example.blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Page<Blog> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Blog> findByCategoryId(Long categoryId, Pageable pageable);
    Page<Blog> findByTitleContainingIgnoreCaseAndCategoryId(String title, Long categoryId, Pageable pageable);
    boolean existsByCategoryId(Long categoryId);
}
