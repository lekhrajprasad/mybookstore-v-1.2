package com.lpras.userratings.dao;

import com.lpras.userratings.entity.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRatingDAO extends JpaRepository<UserRating, Integer> {
    public List<UserRating> getUserRatingByBookId(Integer bookId);
    public List<UserRating> getUserRatingByUserId(String userId);
}
