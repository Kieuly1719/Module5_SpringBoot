package com.codegym.appborrowingbook.repository;

import com.codegym.appborrowingbook.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {
    private final List<Book> books = new ArrayList<>();
    public BookRepository(){
        books.add(new Book(1L, "Dế Mèn Phiêu Lưu Ký", 3));
        books.add(new Book(2L, "Tắt Đèn", 2));
        books.add(new Book(3L, "Lão Hạc", 1));
        books.add(new Book(4L, "Spring Boot Cơ Bản", 0));
    }
    public List<Book> findAll(){
        return books;
    }
    public Optional<Book> findById(Long id){
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }
    public void save(Book book){

    }
}
