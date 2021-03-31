package com.lpras.dto;

import java.io.Serializable;

public class BookRatingInfoDTO implements Serializable{
	
	private static final long serialVersionUID = -7878439367854850043L;
	private Integer bookId;
	private double avgRating;
	private int numberOfSearches;
	
	public BookRatingInfoDTO() {
		super();
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	public int getNumberOfSearches() {
		return numberOfSearches;
	}
	public void setNumberOfSearches(int numberOfSearches) {
		this.numberOfSearches = numberOfSearches;
	}
}
