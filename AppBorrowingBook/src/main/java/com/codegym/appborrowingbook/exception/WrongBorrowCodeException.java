package com.codegym.appborrowingbook.exception;

public class WrongBorrowCodeException extends RuntimeException{
    public WrongBorrowCodeException(String message){
        super(message);
    }
}
