package com.example.baitudien.controller;

import com.example.baitudien.service.DictionaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping({"/", "/dictionary"})
    public String showSearchForm() {
        return "index";
    }

    @PostMapping("/dictionary")
    public String searchWord(@RequestParam("search") String searchWord, Model model) {
        String result = dictionaryService.translate(searchWord);

        model.addAttribute("search", searchWord);
        if (result != null) {
            model.addAttribute("result", result);
        } else {
            model.addAttribute("message", "Không tìm thấy từ này trong từ điển của hệ thống.");
        }

        return "index";
    }
}
