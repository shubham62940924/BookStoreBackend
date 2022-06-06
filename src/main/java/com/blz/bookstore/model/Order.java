package com.blz.bookstore.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="order_table")
public class Order {
    @Id
    @GeneratedValue()
    private int orderId;

    private LocalDateTime orderPlacedTime;

    private String orderStatus;

    private Double totalPrice;

    @OneToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name = "bookId" )
    private BookModel bookModel;

    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "Id")
    private UserRegistrationModel userRegistrationModel;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "addressId" )
    private AddressModel addressModel;


}