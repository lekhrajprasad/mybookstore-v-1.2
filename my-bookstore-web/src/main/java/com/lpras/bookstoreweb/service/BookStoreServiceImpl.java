package com.lpras.bookstoreweb.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.lpras.bookstoreweb.feign.ZuulGetwayFeignClientProxy;
import com.lpras.bookstoreweb.util.StringConstant;
import com.lpras.dto.BookDTO;
import com.lpras.dto.BookInfoDTO;
import com.lpras.dto.OrderDTO;
import com.lpras.dto.OrderInfoDTO;
import com.lpras.dto.OrderItemDTO;
import com.lpras.dto.UserRatingDTO;
import com.lpras.dto.UserRatingInfoDTO;

@Service
@RefreshScope
public class BookStoreServiceImpl implements BookStoreService {
	Map<Integer, BookDTO> booksMap = new LinkedHashMap<>();

	@Value("${bookstore.cloudconfig.msgupdated:defaultMsg}")
	String msg;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	ZuulGetwayFeignClientProxy getwaysFeignProxy;
	// @Autowired
	// BookPriceFeignClientProxy bookPriceProxy;

	// @Autowired
	// BookSearchFeignClientProxy bookSearchProxy;

	// @Autowired
	// UserRatingFeignClientProxy userRatingProxy;

	@Override
	public List<String> getCategoryList() {
		List<String> catList = new ArrayList<>();
		catList.add("All Categories");
		catList.add("Web");
		catList.add("Spring");
		catList.add("Microservices");
		return catList;
	}

	@Override
	public List<String> getAuthorsList() {
		List<String> authors = new ArrayList<>();
		authors.add("All Authors");
		authors.add("Srinivas");
		authors.add("Sri");
		authors.add("Vas");
		authors.add("Lekhraj");
		return authors;
	}

	@Override
	public List<BookDTO> getMyBooks(String author, String category) {
		System.out.println("===============getMyBooks==============");
		System.out.println("msg:::::" + msg);
		System.out.println("===============getMyBooks==============");
		if (author == null || author.length() == 0) {
			author = "All Authors";
		}
		if (category == null || category.length() == 0) {
			category = "All Categories";
		}
		// Invoke Book SearchMS
		List<BookDTO> bookList = getwaysFeignProxy.getBooks(author, category);

		for (BookDTO myBook : bookList) {
			booksMap.put(myBook.getBookId(), myBook);
		}
		return bookList;
	}

	@Override
	public BookInfoDTO getBookInfoByBookId(Integer bookId) {
		return getwaysFeignProxy.getBookById(bookId);
	}

	@Override
	public BookDTO getBookByBookId(Integer bookId) {
		return booksMap.get(bookId);
	}

	public void placeOrder(Map<Integer, BookDTO> mycartMap) {
		List<OrderItemDTO> itemList = new ArrayList<>();
		double totalPrice = 0.0;
		int totalQuantity = 0;
		for (BookDTO mybook : mycartMap.values()) {
			Integer bookId = mybook.getBookId();
			// Invoke BookPrice Controller
			double offeredPrice = getwaysFeignProxy.getOfferedPrice(bookId);
			OrderItemDTO item = new OrderItemDTO(0, bookId, 1, offeredPrice);
			itemList.add(item);
			totalPrice = totalPrice + offeredPrice;
			totalQuantity = totalQuantity + 1;
		}
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		String orderDate = format.format(today);
		OrderDTO order = new OrderDTO(orderDate, "U-111", totalQuantity, totalPrice, "New");
		// SendingOrderMessage to RabbitMQ

		OrderInfoDTO orderInfo = new OrderInfoDTO();
		orderInfo.setOrderDTO(order);
		orderInfo.setItemListDTO(itemList);
		// Sending message to Queue to consume by Place Order MS
		rabbitTemplate.convertAndSend(StringConstant.ORDER_QUEUE, orderInfo);

	}

	@Override
	public void addUserRating(UserRatingDTO userRating) {
		UserRatingInfoDTO userRatingInfoDTO = new UserRatingInfoDTO();
		userRatingInfoDTO.setUserId(userRating.getUserId());
		userRatingInfoDTO.setBookId(userRating.getBookId());
		userRatingInfoDTO.setRating(userRating.getRating());
		userRatingInfoDTO.setRatingId(userRating.getRatingId());
		userRatingInfoDTO.setReview(userRating.getReview());
		rabbitTemplate.convertAndSend(StringConstant.USER_RATING_QUEUE, userRatingInfoDTO);
	}

	@Override
	public List<UserRatingDTO> getMyRatings(String userId) {
		// Invking Book Rating MS
		List<UserRatingDTO> ratingsList = getwaysFeignProxy.getUserRatingByUserId(userId);
		return ratingsList;

	}

}
