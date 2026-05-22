package com.example.baitudien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DictionaryController {

    private static final Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("hello", "Xin chào");
        dictionary.put("book", "Quyển sách");
        dictionary.put("computer", "Máy tính");
        dictionary.put("developer", "Lập trình viên");
        dictionary.put("spring", "Mùa xuân / Spring Framework");
        dictionary.put("student", "Sinh viên / Học sinh");
    }

    @GetMapping({"/", "/dictionary"})
    public String showSearchForm() {
        return "index";
    }

    @PostMapping("/dictionary")
    public String searchWord(@RequestParam("search") String searchWord, Model model) {

        String query = (searchWord != null) ? searchWord.trim().toLowerCase() : "";
        String result = dictionary.get(query);

        model.addAttribute("search", searchWord);
        if (result != null) {
            model.addAttribute("result", result);
        } else {
            model.addAttribute("message", "Không tìm thấy từ này trong từ điển của hệ thống.");
        }

        return "index";
    }
}
