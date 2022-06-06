package com.blz.bookstore.controller;



import com.blz.bookstore.dto.ResponseDTO;
import com.blz.bookstore.model.BookModel;
import com.blz.bookstore.service.ICartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CartServiceController {

    @Autowired
    private ICartService cartService;

    /**
     *
     * @param token
     * @param bookId
     * @return
     */

    @GetMapping("/addbookCart/{bookId}")
    public ResponseEntity<ResponseDTO> addBooksToCart(@RequestHeader("token") String token, @PathVariable int bookId) {
        String message = cartService.addBooktoCart(token,bookId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseDTO("book added to cart", message));
    }

    /**
     *
     * @param token
     * @return
     */
    @GetMapping("/GetCartBookList/{token}")
    public List<BookModel> getCartBookList(@PathVariable String token)
    {
        return cartService.findBookList(token);
    }

    /**
     *
     * @param token
     * @param bookId
     * @return
     */
    @GetMapping("/deleteBookFromCart/{bookId}")
    public ResponseEntity<ResponseDTO> deleteBookFromCart(@RequestHeader("token") String token,@PathVariable int bookId) {
        String message = cartService.deleteBookFromCart(bookId,token);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseDTO("Book is deleted from Cart", message));
    }


}
