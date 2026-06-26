package com.example.blogRest.service;

import com.example.blogRest.model.Blog;
import com.example.blogRest.repository.BlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public List<Blog> findByCategoryId(Long categoryId) {
        return blogRepository.findByCategoryIdOrderByCreatedAtDesc(categoryId);
    }

    @Override
    public Page<Blog> findPage(String keyword, Pageable pageable) {
        if (!StringUtils.hasText(keyword)) {
            return blogRepository.findAllByOrderByCreatedAtDesc(pageable);
        }

        String normalizedKeyword = keyword.trim();
        return blogRepository
                .findByTitleContainingIgnoreCaseOrSummaryContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByCreatedAtDesc(
                        normalizedKeyword,
                        normalizedKeyword,
                        normalizedKeyword,
                        pageable
                );
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }
}
