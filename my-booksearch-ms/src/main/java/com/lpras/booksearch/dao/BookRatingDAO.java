package com.lpras.booksearch.dao;

import com.lpras.booksearch.entity.BookRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRatingDAO extends JpaRepository<BookRating, Integer> {
}
