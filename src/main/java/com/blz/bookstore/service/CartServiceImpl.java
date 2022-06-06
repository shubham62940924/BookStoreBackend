package com.blz.bookstore.service;


/**
 * @author Shubham Singh
 * @version 1.0.0
 * @created 23-05-2022
 */
import com.blz.bookstore.model.BookModel;
import com.blz.bookstore.model.CartItem;
import com.blz.bookstore.model.UserRegistrationModel;
import com.blz.bookstore.repository.BookRepository;
import com.blz.bookstore.repository.CartRepository;
import com.blz.bookstore.repository.UserRegistrationRepository;
import com.blz.bookstore.util.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private UserRegistrationRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartRepository cartRepository;

    /**
     * Add book to the cart
     * @param token
     * @param bookId
     * @return
     */
    @Override
    public String addBooktoCart(String token, int bookId) {

        int userId = TokenUtil.decodeToken(token);

        UserRegistrationModel user = userRepository.findById(userId).orElse(null);

        BookModel book = bookRepository.findById(bookId).orElse(null);

        // if the book present in wishlist and book number is not equal to zero
        int noOfBooks = book.getNoOfBooks();

        if (noOfBooks > 0) {
            List<BookModel> books = findBookList(token);
            if (books == null){
                book.setQuantityInCart(1);
                addBookToRepo(user, book);
//                book.setNoOfBooks(book.getNoOfBooks()-1);
            }
            Optional<BookModel> cartbook = books.stream().filter(t -> t.getBookId() == bookId).findFirst();
            if (cartbook.isPresent()) {
                return "Item is already present in the cart ";
            } else {
                book.setQuantityInCart(1);
                addBookToRepo(user, book);
                book.setNoOfBooks(book.getNoOfBooks()-1);
            }
        }else {
            return "Book is out of Stock !!!";
        }
        return "Item is added in cart !!!";
    }

    /**
     * add book to repo
     * @param user
     * @param book
     * @return
     */

    public String addBookToRepo(UserRegistrationModel user, BookModel book){
        CartItem cart=new CartItem();
        cart.setUserRegistrationModel(user);
        cart.setBookModel(book);
        cart.setCreatedTime(LocalDateTime.now());
        cartRepository.save(cart);
        return "Item added successfully";
    }

    /**
     *To find book list
     * @param token
     * @return
     */
    @Override
    public List<BookModel> findBookList(String token){

        int userId = TokenUtil.decodeToken(token);

       UserRegistrationModel user = userRepository.findById(userId).orElse(null);
        List<CartItem> booklist = cartRepository.findBookById(userId);
        List<BookModel> listOfBook = new ArrayList<BookModel>();
        for (CartItem cartItem : booklist) {
            listOfBook.add(cartItem.getBookModel());
        }
        return listOfBook;
    }

    /**
     *Delete book from cart
     * @param bookId
     * @param token
     * @return
     */
    @Override
    public String deleteBookFromCart( int bookId ,String token) {
        int userId = TokenUtil.decodeToken(token);
        cartRepository.deleteByBookIdandId(bookId, userId);
        return "Book deleted Successfully from cart !!!";
    }

    /**
     *Empty cart
     * @param token
     * @return
     */
    @Override
    public String emptyCart(String token){
        int userId = TokenUtil.decodeToken(token);
        UserRegistrationModel user = userRepository.findById(userId).orElse(null);
        List<CartItem> booklist = cartRepository.findBookById(userId);

        for (CartItem cartItem : booklist) {
            cartRepository.deleteById(cartItem.getCartId());
        }

        return "Cart is Empty";
    }
}