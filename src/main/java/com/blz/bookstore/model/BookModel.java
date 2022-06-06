package com.blz.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Book")
@Data
public class BookModel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int bookId;

    private String bookDetails;
    private String authorName;
    private String bookName;
    private int price;
    private int noOfBooks;
    @Column(name = "image")
    private String image;
    private int discountPrice;
    private int bookRating;
    private int quantityInCart;
//
//    @JsonIgnore
//    @ManyToMany(mappedBy = "BooksList")
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Order> orders;

}
