package com.blz.bookstore.service;


import com.blz.bookstore.dto.BookDTO;
import com.blz.bookstore.exception.UserRegistrationException;
import com.blz.bookstore.model.BookModel;
import com.blz.bookstore.repository.BookRepository;
import com.blz.bookstore.repository.UserRegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author Shubham Singh
 * @version 1.0.0
 * @created 22-05-2022
 */

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService{

    @Autowired
    BookRepository bookStoreRepository;

    @Autowired
    UserRegistrationRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Add book
     * @param bookDTO
     * @return
     */
    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        BookModel addDetails = modelMapper.map(bookDTO, BookModel.class);
        bookStoreRepository.save(addDetails);
        return bookDTO;
    }

    /**
     * get book
     * @return
     */

    @Override
    public List<BookDTO> getBook() {
        List<BookDTO> bookDetails = bookStoreRepository.findAll().stream().map
                        (book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
        if(bookDetails.isEmpty()){
            throw new UserRegistrationException(400,"Book list data is empty");
        }
        return bookDetails;
    }

    /**
     * getting book by id
     * @param id
     * @return
     */
    @Override
    public BookDTO getBookByID(int id) {

        BookModel book = bookStoreRepository.findById(id).
                orElseThrow(() -> new UserRegistrationException(400,"Unable to find any Book detail!"));
        BookDTO bookDetails = modelMapper.map(book, BookDTO.class);
        return bookDetails;
    }

    /**
     * sort price low to high
     * @return
     */
    @Override
    public List<BookModel> sortPriceLowToHigh(){
        return bookStoreRepository.findAllBookByPriceAsc();
    }

    /**
     * high to low
     * @return
     */
    @Override
    public List<BookModel> sortPriceHighToLow(){
        return bookStoreRepository.findAllBookByPriceDsc();
    }
}