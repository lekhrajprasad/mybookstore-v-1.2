package com.lpras.bookstoreweb.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lpras.dto.BookDTO;
import com.lpras.dto.BookInfoDTO;
/*
//@FeignClient(value="MyBookPriceMS",url = "http://localhost:9000")
//@FeignClient(name="my-bookprice-ms")
@FeignClient(name="netflix-zuul-api-gateway-server")
public interface BookPriceFeignClientProxy {
	
	//@GetMapping("/bookPrice/{bookId}")
	//public BookPriceInfo getBookPrice(@PathVariable Integer bookId);
	
	@GetMapping("/my-bookprice-ms/offeredPrice/{bookId}")
	public double getOfferedPrice(@PathVariable Integer bookId);
	
}
*/