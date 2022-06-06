package com.blz.bookstore.service;




import com.blz.bookstore.model.BookModel;
import com.blz.bookstore.model.Order;

import java.util.List;

public interface IOrderService {


    List<BookModel> placeOrder(String token, List<BookModel> orderBookList);
    public List<Order> orderList(String token);
}
