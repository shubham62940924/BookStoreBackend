package com.blz.bookstore.repository;

//import com.bookstore.bookstore.model.Order;

import com.blz.bookstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select * from order_table where id=?",nativeQuery = true)
    List<Order> findOrdersById(int userId);

}
