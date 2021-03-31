package com.lpras.booksearch.service;

import com.lpras.booksearch.entity.Book;
import com.lpras.booksearch.dto.BookInfo;
import com.lpras.booksearch.entity.BookInventory;
import com.lpras.booksearch.entity.BookRating;

import java.util.List;

public interface BookService {
    public List<Book> getBooks(String author, String category);
    public BookInfo getBookInfo(Integer bookId);
    //public void updateBookRating(BookRating bookRating);
    //public void updateBookInventory(BookInventory bookInventory);
}
