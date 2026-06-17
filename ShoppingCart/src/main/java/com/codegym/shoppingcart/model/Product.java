package com.codegym.shoppingcart.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private double oldPrice;
    private double newPrice;
    private String image;
    @Column(length = 1000)
    private String description;
}
