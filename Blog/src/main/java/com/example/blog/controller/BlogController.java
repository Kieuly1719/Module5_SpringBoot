package com.example.blog.controller;

import com.example.blog.model.Blog;
import com.example.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    public BlogService blogService;
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
    @GetMapping
    public String getAllBlogs(Model model){
        model.addAttribute("blogs", blogService.findAll());
        return "blogs/list";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model){
        model.addAttribute("blog", new Blog());
        return "blogs/create";
    }
    @GetMapping("/{id}")
    public String viewBlog(@PathVariable Long id, Model model){
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "blogs/detail";
    }
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model){
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "blogs/edit";
    }
    @PostMapping("/{id}/edit")
    public String updateBlog(@PathVariable Long id, Blog blog){
        Blog oldBlog = blogService.findById(id);
        oldBlog.setTitle(blog.getTitle());
        oldBlog.setSummary(blog.getSummary());
        oldBlog.setContent(blog.getContent());
        blogService.save(oldBlog);
        return "redirect:/blogs";
    }
    @PostMapping("/{id}/delete")
    public String deleteBlog(@PathVariable Long id){
        blogService.delete(id);
        return "redirect:/blogs";
    }
    @PostMapping("/create")
    public String createBlog(Blog blog){
        blogService.save(blog);
        return "blogs/list";
    }
}
