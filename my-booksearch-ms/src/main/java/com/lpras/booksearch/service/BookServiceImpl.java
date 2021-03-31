package com.lpras.booksearch.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lpras.booksearch.dao.BookDAO;
import com.lpras.booksearch.dao.BookInventoryDAO;
import com.lpras.booksearch.dao.BookRatingDAO;
import com.lpras.booksearch.dto.BookInfo;
import com.lpras.booksearch.dto.BookPriceInfo;
import com.lpras.booksearch.entity.Book;
import com.lpras.booksearch.entity.BookInventory;
import com.lpras.booksearch.entity.BookRating;
import com.lpras.booksearch.feign.BookPriceFeignClientProxy;
import com.lpras.booksearch.util.StringConstant;
import com.lpras.dto.BookInventoryInfoDTO;
import com.lpras.dto.BookRatingInfoDTO;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	static Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	BookPriceFeignClientProxy bookPriceProxy;
	
	@Autowired
	BookDAO bookDAO;

	@Autowired
	BookRatingDAO ratingDAO;

	@Autowired
	BookInventoryDAO inventoryDAO;

	@Override
	public List<Book> getBooks(String author, String category) {
		List<Book> myBooks = new ArrayList<>();
		if ("All Authors".equals(author) && "All Categories".equals(category)) {
			myBooks = bookDAO.findAll();
		} else if (!"All Authors".equals(author) && "All Categories".equals(category)) {
			myBooks = bookDAO.getBooksByAuthor(author);
		} else if ("All Authors".equals(author) && !"All Categories".equals(category)) {
			myBooks = bookDAO.getBooksByCategory(category);
		} else {
			myBooks = bookDAO.getBooksByAuthorAndCategory(author, category);
		}
		return myBooks;
	}

	@Override
	public BookInfo getBookInfo(Integer bookId) {
		BookInfo bookInfo = new BookInfo();
		// 1. getBookDetails
		Book book = bookDAO.findById(bookId).get();
		bookInfo.setBookId(book.getBookId());
		bookInfo.setBookName(book.getBookName());
		bookInfo.setAuthor(book.getAuthor());
		bookInfo.setPublications(book.getPublications());
		bookInfo.setCategory(book.getCategory());
		// 2. get book ratiing details
		BookRating bookRating = ratingDAO.findById(bookId).get();
		bookInfo.setAvgRating(bookRating.getAvgRating());
		bookInfo.setNumberOfsearches(bookRating.getNumberOfSearches());
		// 3. get book inventry details
		BookInventory bookInventory = inventoryDAO.findById(bookId).get();
		bookInfo.setBooksAvailable(bookInventory.getBooksAvailable());
		// 4. get boo price details
		
		//Template replaced with Feign Client
		BookPriceInfo bookPriceInfo = bookPriceProxy.getBookPrice(bookId);
		bookInfo.setPrice(bookPriceInfo.getPrice());//9
		bookInfo.setOffer(bookPriceInfo.getOffer());//10

		return bookInfo;
	}

	@RabbitListener(queues = StringConstant.RATINGS_QUEUE)
	public void updateBookRating(BookRatingInfoDTO bookRatingInfo) {
		BookRating bookRating = new BookRating();
		bookRating.setAvgRating(bookRatingInfo.getAvgRating());
		bookRating.setBookId(bookRatingInfo.getBookId());
		bookRating.setNumberOfSearches(bookRatingInfo.getNumberOfSearches());
		ratingDAO.save(bookRating);
	}

	@RabbitListener(queues = StringConstant.INVENTORY_QUEUE)
	public void updateBookInventory(BookInventoryInfoDTO bookInventoryInfo) {
		BookInventory bookInventory = new BookInventory();
		bookInventory.setBookId(bookInventoryInfo.getBookId());
		bookInventory.setBooksAvailable(bookInventoryInfo.getBooksAvailable());
		inventoryDAO.save(bookInventory);
	}

}
