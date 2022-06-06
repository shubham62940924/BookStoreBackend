package com.blz.bookstore.controller;



import com.blz.bookstore.dto.ResponseDTO;
import com.blz.bookstore.model.BookModel;
import com.blz.bookstore.service.IWishlistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class WishlistController {

    @Autowired
    private IWishlistService WishlistService;

    /**
     *
     *
     * @param token
     * @param bookId
     * @return
     */

    @GetMapping("/addbookWishlist/{bookId}")
    public ResponseEntity<ResponseDTO> addBooktoWishlist(@RequestHeader("token") String token, @PathVariable int bookId) {
        String message = WishlistService.addBooktoWishlist(token,bookId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseDTO("book added to Wishlist", message));

    }

    /**
     *
     * @param token
     * @return
     */
    @GetMapping("/getWishlistofUser/{token}")
    public List<BookModel> getCartBookList(@PathVariable String token){
        return WishlistService.findBookList(token);
    }

    /**
     *
     * @param token
     * @param bookId
     * @return
     */
    @GetMapping("/deleteBookFromWishList/{bookId}")
    public ResponseEntity<ResponseDTO> deleteBookFromCart(@RequestHeader("token") String token,@PathVariable int bookId) {
        String message = WishlistService.deleteBookFromWishList(token,bookId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseDTO("Book is deleted from Wishlist", message));
    }
}
