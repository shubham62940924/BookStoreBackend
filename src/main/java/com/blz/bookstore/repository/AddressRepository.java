package com.blz.bookstore.repository;

//import com.bookstore.bookstore.model.AddressModel;

import com.blz.bookstore.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Integer> {
}
