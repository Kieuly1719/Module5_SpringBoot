package com.codegym.appborrowingbook.controller;

import com.codegym.appborrowingbook.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/return")
public class ReturnController {
    private final BookService bookService;

    public ReturnController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String showReturnForm() {
        return "return";
    }

    @PostMapping
    public String returnBook(@RequestParam String code) {

        bookService.returnBook(code);

        return "return-success";
    }
}
