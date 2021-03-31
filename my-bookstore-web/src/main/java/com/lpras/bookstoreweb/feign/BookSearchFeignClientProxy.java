package com.lpras.bookstoreweb.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lpras.dto.BookDTO;
import com.lpras.dto.BookInfoDTO;
/*
//@FeignClient(value="MyBookSearchMS",url = "http://localhost:8000")
//@FeignClient(name="MyApiGatewayServer")
//@FeignClient(value="MyBookSearchMS")
@FeignClient(name="netflix-zuul-api-gateway-server")
public interface BookSearchFeignClientProxy {
	
	//@GetMapping("/MyBookSearchMS/mybooks/{author}/{category}")
	@GetMapping("/MyBookSearchMS/mybooks/{author}/{category}")
	public List<BookDTO> getBooks(@PathVariable String author, @PathVariable String category);

	@GetMapping("/MyBookSearchMS/mybooks/{bookId}")
	public BookInfoDTO getBookById(@PathVariable Integer bookId);
}
*/
