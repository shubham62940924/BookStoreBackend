package com.blz.bookstore.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AddressModel {
    @Id
    @GeneratedValue()
    private int addressId;

    private String address;
    private String state;
    private String city;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id")
    private UserRegistrationModel userRegistrationModel;


}