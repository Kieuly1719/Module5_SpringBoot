package com.codegym.appborrowingbook.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class BorrowCodeRepository {
    private final Map<String, Long> borrowCodes = new HashMap<>();
    public void save(String code, Long bookId){
        borrowCodes.put(code, bookId);
    }
    public Optional<Long> findBookIdByCode(String code) {
        return Optional.ofNullable(borrowCodes.get(code));
    }

    public boolean existsByCode(String code) {
        return borrowCodes.containsKey(code);
    }

    public void deleteByCode(String code) {
        borrowCodes.remove(code);
    }
}
