package com.example.baitudien.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DictionaryService {

    private static final Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("hello", "Xin chào");
        dictionary.put("book", "Quyển sách");
        dictionary.put("computer", "Máy tính");
        dictionary.put("developer", "Lập trình viên");
        dictionary.put("spring", "Mùa xuân / Spring Framework");
        dictionary.put("student", "Sinh viên / Học sinh");
    }

    public String translate(String word) {
        String query = (word != null) ? word.trim().toLowerCase() : "";
        return dictionary.get(query);
    }
}
