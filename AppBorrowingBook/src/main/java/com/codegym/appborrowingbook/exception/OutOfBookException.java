package com.codegym.appborrowingbook.exception;

public class OutOfBookException extends RuntimeException{
    public OutOfBookException(String message){
        super(message);
    }
}
