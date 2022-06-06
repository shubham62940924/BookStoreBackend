package com.blz.bookstore.controller;



import com.blz.bookstore.dto.BookDTO;
import com.blz.bookstore.dto.ResponseDTO;
import com.blz.bookstore.model.BookModel;
import com.blz.bookstore.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import java.util.List;

@RestController
@RequestMapping("/book")
@Slf4j
/**
 * Cross-Origin Resource Sharing (CORS) is a security concept that
 * allows restricting the resources implemented in web browsers. It
 * prevents the JavaScript code producing or consuming the requests
 * against different origin.
 */
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class BookStoreController {


    @Autowired
    BookService bookService;

    /**
     *
     * @param bookDTO
     * @return
     */
    @PostMapping("/addBookDetails")
    public ResponseEntity<ResponseDTO> addBookDetails(@RequestBody BookDTO bookDTO) {
        BookDTO addData = bookService.addBook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Added newBook Details", addData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("/getBookDetails")
    public ResponseEntity<ResponseDTO> getBookDetails() {
        List<BookDTO> bookData = bookService.getBook();
        ResponseDTO responseDTO = new ResponseDTO("Fetched All Book Details", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getBookDetailsByID/{id}")
    public ResponseEntity<ResponseDTO> getBookDetailsByID(@PathVariable int id) {
        BookDTO bookDTO = bookService.getBookByID(id);
        ResponseDTO responseDTO = new ResponseDTO("Fetched by ID : Book Details", bookDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("/sortByPriceAsc")
    public ResponseEntity<ResponseDTO> getBookByPriceAsc() {
        List<BookModel> bookData = bookService.sortPriceLowToHigh();
        ResponseDTO responseDTO = new ResponseDTO("Sorted all books by price low to high ", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("/sortByPriceDsc")
    public ResponseEntity<ResponseDTO> getBookByPriceDsc() {
        List<BookModel> bookData = bookService.sortPriceHighToLow();
        ResponseDTO responseDTO = new ResponseDTO("Sorted all books by price high to low ", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
