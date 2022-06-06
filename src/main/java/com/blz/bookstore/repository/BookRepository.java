package com.blz.bookstore.repository;


import com.blz.bookstore.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<BookModel, Integer> {

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM book ORDER BY price ASC" , nativeQuery = true)
    List<BookModel> findAllBookByPriceAsc();


    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM book ORDER BY price DESC" , nativeQuery = true)
    List<BookModel> findAllBookByPriceDsc();


}