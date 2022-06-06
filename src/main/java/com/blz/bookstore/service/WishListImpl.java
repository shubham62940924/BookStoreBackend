package com.blz.bookstore.service;



import com.blz.bookstore.model.BookModel;
import com.blz.bookstore.model.UserRegistrationModel;
import com.blz.bookstore.model.Wishlist;
import com.blz.bookstore.repository.BookRepository;
import com.blz.bookstore.repository.UserRegistrationRepository;
import com.blz.bookstore.repository.WishlistRepository;
import com.blz.bookstore.util.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishListImpl implements IWishlistService{

    @Autowired
    private UserRegistrationRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    /**
     *
     * @param token
     * @param bookId
     * @return
     */



    @Override
    public String addBooktoWishlist(String token, int bookId) {

        int userId = TokenUtil.decodeToken(token);
        UserRegistrationModel user = userRepository.findById(userId).orElse(null);
        BookModel book = bookRepository.findById(bookId).orElse(null);
        // if the book present in wishlist and book number is not equal to zero
        int noOfBooks = book.getNoOfBooks();
        if (noOfBooks > 0) {
            List<BookModel> books = findBookList(token);
            if (books == null){
                addBookToRepo(user, book);
                book.setNoOfBooks(book.getNoOfBooks()-1);
            }
            Optional<BookModel> cartbook = books.stream().filter(t -> t.getBookId() == bookId).findAny();
            if (cartbook.isPresent()) {
                return "Item is already present in the cart ";
            } else {
                addBookToRepo(user, book);
//                book.setNoOfBooks(book.getNoOfBooks()-1);
            }
        }
        return "Book is out off stock !!!";
    }

    /**
     *
     * @param token
     * @return
     */
    @Override
    public List<BookModel> findBookList(String token) {
        int userId = TokenUtil.decodeToken(token);

        UserRegistrationModel user = userRepository.findById(userId).orElse(null);
        List<Wishlist> booklist = wishlistRepository.findBookById(userId);
        List<BookModel> listOfBook = new ArrayList<BookModel>();
        for (int i=0; i<booklist.size();i++) {
            listOfBook.add(booklist.get(i).getBookModel());
        }
        return listOfBook;
    }

    /**
     *
     * @param user
     * @param book
     * @return
     */

    public String addBookToRepo(UserRegistrationModel user, BookModel book){
        Wishlist wishlist=new Wishlist();
        wishlist.setUserRegistrationModel(user);
        wishlist.setBookModel(book);
        wishlist.setCreatedTime(LocalDateTime.now());
        wishlistRepository.save(wishlist);
        return "Item added successfully";
    }

    /**
     *
     * @param token
     * @param bookId
     * @return
     */

    @Override
    public String deleteBookFromWishList(String token, int bookId) {
        int userId = TokenUtil.decodeToken(token);
        wishlistRepository.deleteByBookIdandId(bookId, userId);

        return "Book deleted Successfully from Wishlist !!!";
    }
}
