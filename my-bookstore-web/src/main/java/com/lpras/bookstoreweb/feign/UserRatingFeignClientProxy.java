package com.lpras.bookstoreweb.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lpras.dto.UserRatingDTO;
/*
//@FeignClient(value="MyUserRatingMS",url = "http://localhost:6500")
@FeignClient(name="MyUserRatingMS")
public interface UserRatingFeignClientProxy {
	
	@GetMapping("/userRatings/{userId}")
	public List<UserRatingDTO> getUserRatingByUserId(@PathVariable String userId);
	
	//@GetMapping("/bookRatings/{bookId}")
	//public BookRatingInfoDTO getBookRetingByBookId(@PathVariable Integer bookId);
	
	
}
*/