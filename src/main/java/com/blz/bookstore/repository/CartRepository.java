package com.blz.bookstore.repository;


import com.blz.bookstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Integer> {

    @Query(value = "select * from cart_item where id=?",nativeQuery = true)
    List<CartItem> findBookById(int userId);
//
  @Modifying
    @Transactional
    @Query(value = "delete from cart_item where book_id =? and id=?",nativeQuery = true)
    void deleteByBookIdandId(int bookId, int Id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE from cart_item where book_id =? and id=?",nativeQuery = true)
    void UpdateByBookIdandId(int bookId, int Id);


//
//    @Modifying
//    @Transactional
//    @Query(value = "delete from cart_item where  book_id=?",nativeQuery = true)
//    void deleteById( int Id);
}