package com.codegym.appborrowingbook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Book {
    private Long id;
    private String name;
    private int quantity;

    public void decreaseQuantity(){
        this.quantity--;
    }
    public void increaseQuantity(){
        this.quantity++;
    }
}
