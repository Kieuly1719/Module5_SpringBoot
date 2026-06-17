package com.codegym.appborrowingbook.service;

import com.codegym.appborrowingbook.exception.OutOfBookException;
import com.codegym.appborrowingbook.exception.WrongBorrowCodeException;
import com.codegym.appborrowingbook.model.Book;
import com.codegym.appborrowingbook.repository.BookRepository;
import com.codegym.appborrowingbook.repository.BorrowCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BorrowCodeRepository borrowCodeRepository;

    public BookService(BookRepository bookRepository, BorrowCodeRepository borrowCodeRepository){
        this.bookRepository = bookRepository;
        this.borrowCodeRepository = borrowCodeRepository;
    }
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sách"));
    }

    public String borrowBook(Long bookId) {
        Book book = findById(bookId);

        if (book.getQuantity() <= 0) {
            throw new OutOfBookException("Sách đã hết, không thể mượn");
        }

        book.decreaseQuantity();
        bookRepository.save(book);

        String code = generateBorrowCode();
        borrowCodeRepository.save(code, bookId);

        return code;
    }

    public void returnBook(String code) {
        Long bookId = borrowCodeRepository.findBookIdByCode(code)
                .orElseThrow(() -> new WrongBorrowCodeException("Mã mượn sách không hợp lệ"));

        Book book = findById(bookId);

        book.increaseQuantity();
        bookRepository.save(book);

        borrowCodeRepository.deleteByCode(code);
    }

    private String generateBorrowCode() {
        Random random = new Random();
        String code;

        do {
            code = String.valueOf(10000 + random.nextInt(90000));
        } while (borrowCodeRepository.existsByCode(code));

        return code;
    }
}
