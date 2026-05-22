package com.example.baitudien.repository;

import java.util.HashMap;
import java.util.Map;

public class DictionaryRepository implements IDictionaryRepository{
    private static final Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("hello", "Xin chào");
        dictionary.put("book", "Quyển sách");
        dictionary.put("computer", "Máy tính");
        dictionary.put("developer", "Lập trình viên");
        dictionary.put("spring", "Mùa xuân / Spring Framework");
        dictionary.put("student", "Sinh viên / Học sinh");
    }

    @Override
    public String translate(String word) {
        String query = (word != null) ? word.trim().toLowerCase() : "";
        return dictionary.get(query);
    }
}
