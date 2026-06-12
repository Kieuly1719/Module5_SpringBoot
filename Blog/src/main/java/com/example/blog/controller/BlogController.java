package com.example.blog.controller;

import com.example.blog.model.Blog;
import com.example.blog.model.Category;
import com.example.blog.service.BlogService;
import com.example.blog.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    private final BlogService blogService;
    private final CategoryService categoryService;

    public BlogController(BlogService blogService, CategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAllBlogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(required = false) Long categoryId,
            Model model
    ) {
        Pageable pageable = PageRequest.of(
                Math.max(page, 0),
                5,
                Sort.by("createdAt").descending()
        );

        String normalizedKeyword = keyword.trim();
        Page<Blog> blogs;
        if (!normalizedKeyword.isBlank() && categoryId != null) {
            blogs = blogService.searchByTitleAndCategory(normalizedKeyword, categoryId, pageable);
        } else if (!normalizedKeyword.isBlank()) {
            blogs = blogService.searchByTitle(normalizedKeyword, pageable);
        } else if (categoryId != null) {
            blogs = blogService.findByCategory(categoryId, pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }

        model.addAttribute("blogs", blogs);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("keyword", normalizedKeyword);
        model.addAttribute("categoryId", categoryId);
        return "blogs/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.findAll());
        return "blogs/create";
    }

    @PostMapping("/create")
    public String createBlog(Blog blog, @RequestParam Long categoryId) {
        Category category = categoryService.findById(categoryId);
        if (category == null) {
            return "redirect:/blogs/create";
        }
        blog.setCategory(category);
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "blogs/detail";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        model.addAttribute("categories", categoryService.findAll());
        return "blogs/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateBlog(@PathVariable Long id, Blog blog, @RequestParam Long categoryId) {
        Blog oldBlog = blogService.findById(id);
        Category category = categoryService.findById(categoryId);
        if (oldBlog == null || category == null) {
            return "redirect:/blogs";
        }

        oldBlog.setTitle(blog.getTitle());
        oldBlog.setSummary(blog.getSummary());
        oldBlog.setContent(blog.getContent());
        oldBlog.setCategory(category);
        blogService.save(oldBlog);
        return "redirect:/blogs";
    }

    @PostMapping("/{id}/delete")
    public String deleteBlog(@PathVariable Long id) {
        if (blogService.findById(id) != null) {
            blogService.delete(id);
        }
        return "redirect:/blogs";
    }
}
