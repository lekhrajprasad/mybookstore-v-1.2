package com.lpras.booksearch.dao;

import com.lpras.booksearch.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Integer> {
    public List<Book> getBooksByAuthorAndCategory(String author, String category);
    public List<Book> getBooksByAuthor(String author);
    public List<Book> getBooksByCategory(String category);
}
