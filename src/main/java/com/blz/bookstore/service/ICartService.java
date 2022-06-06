package com.blz.bookstore.service;

//mport com.bookstore.bookstore.model.BookModel;


import com.blz.bookstore.model.BookModel;

import javax.transaction.Transactional;
import java.util.List;

public interface ICartService {

    @Transactional
    String addBooktoCart(String token, int bookId);

    List<BookModel> findBookList(String token);

    String deleteBookFromCart(int bookId ,String token);

    String emptyCart(String token);

    String UpdateBookFromCart(int bookId ,String token);
}