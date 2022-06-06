package com.blz.bookstore.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue
    private int cartId;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "bookId")
    private BookModel bookModel;

    @OneToOne()
    @JoinColumn(name = "cartId")
    private UserRegistrationModel userRegistrationModel;

    private LocalDateTime createdTime;


}