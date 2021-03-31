package com.lpras.dto;

import java.io.Serializable;

public class UserRatingInfoDTO implements Serializable{
	private static final long serialVersionUID = 8162946822726066788L;
	private Integer ratingId;
	private String userId;
	private Integer bookId;
	private double rating;
	private String review;
	public UserRatingInfoDTO() {
		super();
	}
	public UserRatingInfoDTO(Integer ratingId, String userId, Integer bookId, double rating, String review) {
		super();
		this.ratingId = ratingId;
		this.userId = userId;
		this.bookId = bookId;
		this.rating = rating;
		this.review = review;
	}
	public Integer getRatingId() {
		return ratingId;
	}
	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
}
