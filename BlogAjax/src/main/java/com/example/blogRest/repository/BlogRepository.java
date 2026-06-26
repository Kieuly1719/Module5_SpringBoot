package com.example.blogRest.repository;

import com.example.blogRest.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByOrderByCreatedAtDesc();

    List<Blog> findByCategoryIdOrderByCreatedAtDesc(Long categoryId);

    Page<Blog> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Blog> findByTitleContainingIgnoreCaseOrSummaryContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByCreatedAtDesc(
            String titleKeyword,
            String summaryKeyword,
            String contentKeyword,
            Pageable pageable
    );
}
