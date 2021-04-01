package com.lpras.userratings.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lpras.userratings.entity.BookRating;
import com.lpras.userratings.entity.UserRating;
import com.lpras.userratings.service.RatingService;

@CrossOrigin
@RestController
@RefreshScope
public class RatingController {
    static Logger log = LoggerFactory.getLogger(RatingController.class);
    
    @Autowired
    RatingService ratingService;
    
    @Value("${server.port}")
    String port;
    
    @Value("${userrating.cloudconfig.msgupdated}")
    String msg;
    
    @GetMapping("/userRatings/{userId}")
    public List<UserRating> getUserRatingByUserId(@PathVariable String userId){ 
    	System.out.println("=============================::port:::::"+port);
        System.out.println("=============================::msg:::::"+msg);
        return ratingService.getUserRatingByUserId(userId);
    }
    @GetMapping("/bookRatings/{bookId}")
    public BookRating getBookRatingByBookId(@PathVariable int bookId){
    	System.out.println("=============================::port:::::"+port);
        System.out.println("=============================::msg:::::"+msg);
        return ratingService.getBookRatingByBookId(bookId);
    }
    
}
