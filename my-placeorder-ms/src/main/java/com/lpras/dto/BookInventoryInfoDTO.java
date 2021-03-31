package com.lpras.dto;

import java.io.Serializable;

public class BookInventoryInfoDTO implements Serializable{
	
	private static final long serialVersionUID = -1403586375398586702L;
	private Integer bookId;
	private int booksAvailable;
	
	public BookInventoryInfoDTO() {
		super();
	}
	public BookInventoryInfoDTO(Integer bookId, int booksAvailable) {
		super();
		this.bookId = bookId;
		this.booksAvailable = booksAvailable;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public int getBooksAvailable() {
		return booksAvailable;
	}
	public void setBooksAvailable(int booksAvailable) {
		this.booksAvailable = booksAvailable;
	}
	
}
