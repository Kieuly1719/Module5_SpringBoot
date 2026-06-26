package com.example.blogRest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogPageController {
    @GetMapping({"/", "/blogs"})
    public String index() {
        return "blogs/index";
    }
}
