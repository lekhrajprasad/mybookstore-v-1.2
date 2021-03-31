package com.lpras.booksearch.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lpras.booksearch.dto.BookPriceInfo;

//@FeignClient(value="abc",url = "http://localhost:9000")
//@FeignClient(name="MyApiGatewayServer")
@FeignClient(name="netflix-zuul-api-gateway-server")
//@FeignClient(name="MyBookPriceMS")
public interface BookPriceFeignClientProxy {
	
	@GetMapping("/my-bookprice-ms/bookPrice/{bookId}")
	public BookPriceInfo getBookPrice(@PathVariable Integer bookId);
}
