package com.blz.bookstore.dto;
/**
 * @author Shubham singh
 * @version 1.0.0
 * @created 23-05-2022
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private int bookId;
    private String bookDetails;
    private String authorName;
    private String bookName;
    private int price;
    private int discountPrice;
    private int noOfBooks;
    private String image;
    private int bookRating;
}