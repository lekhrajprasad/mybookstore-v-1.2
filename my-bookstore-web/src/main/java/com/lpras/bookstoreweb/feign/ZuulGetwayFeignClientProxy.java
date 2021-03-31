package com.lpras.bookstoreweb.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lpras.dto.BookDTO;
import com.lpras.dto.BookInfoDTO;
import com.lpras.dto.OrderInfoDTO;
import com.lpras.dto.UserRatingDTO;

@FeignClient(name = "netflix-zuul-api-gateway-server")
public interface ZuulGetwayFeignClientProxy {

	@GetMapping("/my-bookprice-ms/offeredPrice/{bookId}")
	public double getOfferedPrice(@PathVariable Integer bookId);

	@GetMapping("/my-booksearch-ms/mybooks/{author}/{category}")
	public List<BookDTO> getBooks(@PathVariable String author, @PathVariable String category);

	@GetMapping("/my-booksearch-ms/mybooks/{bookId}")
	public BookInfoDTO getBookById(@PathVariable Integer bookId);
	
	@GetMapping("/my-placeorder-ms/myorders/{userId}")
    public List<OrderInfoDTO> getOrdersByUserId(@PathVariable String userId);

    @GetMapping("/my-placeorder-ms/myorder/{orderId}")
    public OrderInfoDTO getOrdersByUserId(@PathVariable Integer orderId);
    
    @GetMapping("/my-userrating-ms/userRatings/{userId}")
	public List<UserRatingDTO> getUserRatingByUserId(@PathVariable String userId);
}
