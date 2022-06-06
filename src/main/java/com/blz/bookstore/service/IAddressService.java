package com.blz.bookstore.service;

//import com.bookstore.bookstore.dto.AddressDTO;
//import com.bookstore.bookstore.model.AddressModel;


import com.blz.bookstore.dto.AddressDTO;
import com.blz.bookstore.model.AddressModel;

public interface IAddressService {

    AddressModel addAddress(AddressDTO address, String token);
}
