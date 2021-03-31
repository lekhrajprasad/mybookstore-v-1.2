package com.lpras.userratings.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lpras.dto.BookRatingInfoDTO;
import com.lpras.dto.UserRatingInfoDTO;
import com.lpras.userratings.dao.BookRatingDAO;
import com.lpras.userratings.dao.UserRatingDAO;
import com.lpras.userratings.entity.BookRating;
import com.lpras.userratings.entity.UserRating;
import com.lpras.userratings.util.StringConstant;

@Service
@Transactional
public class RatingServiceImpl implements RatingService{

	@Autowired
	BookRatingDAO bookRatingDAO;

	@Autowired
	UserRatingDAO userRatingDAO;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RabbitListener(queues = StringConstant.USER_RATING_QUEUE)
	public void addUserRating(UserRatingInfoDTO userRating) {
		UserRating userRating1 = new UserRating(userRating.getUserId(), userRating.getRating(), userRating.getReview(),userRating.getBookId());
		//1. Add the user rating
		userRatingDAO.save(userRating1);

		//2. calculate the avg rating for book
		int bookId = userRating.getBookId();
		List<UserRating> userRatings = userRatingDAO.getUserRatingByBookId(bookId);
		double sumRating=0.0;
		for(UserRating ur: userRatings){
			sumRating = sumRating + ur.getRating();
		}
		double avgRating = sumRating/userRatings.size();

		//3. Update BookRating in UserRatingMS(Local)
		BookRating bookRating = bookRatingDAO.findById(bookId).get();
		bookRating.setAvgRating(avgRating);
		bookRatingDAO.save(bookRating);

		//4. Update book rating in BookSearchMS By Sending Message to RabbitMQ
		BookRatingInfoDTO bookRatingInfo  = new BookRatingInfoDTO();
		bookRatingInfo.setBookId(bookRating.getBookId());
		bookRatingInfo.setAvgRating(bookRating.getAvgRating());
		bookRatingInfo.setNumberOfSearches(bookRating.getNumberOfSearches());
		rabbitTemplate.convertAndSend(StringConstant.RATINGS_QUEUE, bookRatingInfo);
		
	}

	@Override
	public List<UserRating> getUserRatingByUserId(String userId) {
		return userRatingDAO.getUserRatingByUserId(userId);
	}

	@Override
	public void updateBookRating(BookRating bookRating) {
		bookRatingDAO.save(bookRating);
	}

	@Override
	public BookRating getBookRatingByBookId(Integer bookId) {
		return bookRatingDAO.findById(bookId).get();
	}

    
}
