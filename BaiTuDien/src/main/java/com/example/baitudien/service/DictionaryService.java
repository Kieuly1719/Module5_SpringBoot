package com.example.baitudien.service;

import com.example.baitudien.repository.DictionaryRepository;
import com.example.baitudien.repository.IDictionaryRepository;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {
    private final IDictionaryRepository dictionaryRepository = new DictionaryRepository();
    public String translate(String word) {
        return dictionaryRepository.translate(word);
    }
}
