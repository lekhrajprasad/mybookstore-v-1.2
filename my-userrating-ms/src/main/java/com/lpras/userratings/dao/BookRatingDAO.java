package com.lpras.userratings.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpras.userratings.entity.BookRating;

public interface BookRatingDAO extends JpaRepository<BookRating, Integer> {
    
}
