package com.codegym.appborrowingbook.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OutOfBookException.class)
    public String handleOutOfBookException(OutOfBookException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(WrongBorrowCodeException.class)
    public String handleWrongBorrowCodeException(WrongBorrowCodeException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("message", "Có lỗi xảy ra: " + ex.getMessage());
        return "error";
    }
}