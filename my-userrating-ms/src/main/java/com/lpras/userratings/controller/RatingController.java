package com.lpras.userratings.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lpras.userratings.entity.BookRating;
import com.lpras.userratings.entity.UserRating;
import com.lpras.userratings.service.RatingService;

@CrossOrigin
@RestController
public class RatingController {
    static Logger log = LoggerFactory.getLogger(RatingController.class);
    
    @Autowired
    RatingService ratingService;
    
    @GetMapping("/userRatings/{userId}")
    public List<UserRating> getUserRatingByUserId(@PathVariable String userId){ 
        return ratingService.getUserRatingByUserId(userId);
    }
    @GetMapping("/bookRatings/{bookId}")
    public BookRating getBookRatingByBookId(@PathVariable int bookId){
        return ratingService.getBookRatingByBookId(bookId);
    }
    
}
