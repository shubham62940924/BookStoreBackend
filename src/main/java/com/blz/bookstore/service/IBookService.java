package com.blz.bookstore.service;

//import com.bookstore.bookstore.dto.BookDTO;
//import com.bookstore.bookstore.model.BookModel;


import com.blz.bookstore.dto.BookDTO;
import com.blz.bookstore.model.BookModel;

import java.util.List;

public interface IBookService {
    List<BookDTO> getBook();
    BookDTO addBook(BookDTO bookDTO);
    BookDTO getBookByID(int id);
    public List<BookModel> sortPriceLowToHigh();
    public List<BookModel> sortPriceHighToLow();

}
