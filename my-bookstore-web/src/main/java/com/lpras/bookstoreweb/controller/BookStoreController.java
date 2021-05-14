package com.lpras.bookstoreweb.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lpras.bookstoreweb.service.BookStoreService;
import com.lpras.dto.BookDTO;
import com.lpras.dto.BookInfoDTO;
import com.lpras.dto.UserRatingDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@CrossOrigin
@Controller
@RefreshScope
public class BookStoreController {
	static Logger log = LoggerFactory.getLogger(BookStoreController.class);

	@Autowired
	private BookStoreService bookStoreService;
	private Map<Integer, BookDTO> mycartMap = new LinkedHashMap<>();

	@Value("${bookstore.cloudconfig.msgupdated:defaultMsg}")
	String msg;

	@GetMapping("/")
	public String showIndexPage(Model model, HttpSession session) {
		System.out.println("=============================::msg:::::" + msg);
		List<String> authorList = bookStoreService.getAuthorsList();
		List<String> catList = bookStoreService.getCategoryList();
		session.setAttribute("MyAuthorList", authorList);
		session.setAttribute("MyCatList", catList);
		return "redirect:/showAllBooks";
	}

	@HystrixCommand(fallbackMethod = "showAllBooksFallback")
	@GetMapping("/showAllBooks")
	public String showBooksList(HttpServletRequest request, HttpSession session) {
		// if(1==1)
		// throw new ArithmeticException();
		String author = request.getParameter("author");
		String category = request.getParameter("category");
		Collection<BookDTO> blist = bookStoreService.getMyBooks(author, category);
		session.setAttribute("MyBooksList", blist);
		session.setAttribute("MyCart", mycartMap);
		return "showBooksList";
	}

	public String showAllBooksFallback(HttpServletRequest request, HttpSession session) {
		log.info("---BookStoreController---showAllBooksFallback()---");
		Collection<BookDTO> blist = new ArrayList<>();
		BookDTO dto = new BookDTO();
		blist.add(dto);
		session.setAttribute("MyBooksList", blist);
		session.setAttribute("MyCart", mycartMap);
		return "showBooksList";
	}

	@GetMapping("/showBookInfo")
	public String showBookFullInfo(@RequestParam("bookId") String bookId, HttpSession session,
			HttpServletRequest request) {
		BookInfoDTO bookInfoDTO = bookStoreService.getBookInfoByBookId(Integer.parseInt(bookId));
		request.setAttribute("MyBookInfo", bookInfoDTO);
		return "showBookInfo";
	}

	@PostMapping("/addToCart")
	public String addBookToCart(@RequestParam("bookId") String bookId, HttpSession session,
			HttpServletRequest request) {
		BookDTO bookDTO = bookStoreService.getBookByBookId(Integer.parseInt(bookId));
		mycartMap.put(Integer.parseInt(bookId), bookDTO);
		session.setAttribute("MyCart", mycartMap);
		return "showBooksList";
	}

	@GetMapping("/showMyCart")
	public String showBooksInMyCart(HttpServletRequest request, HttpSession session) {
		Object obj = session.getAttribute("MyCart");
		Map<Integer, BookDTO> cartMap = (Map<Integer, BookDTO>) obj;
		Collection<BookDTO> cartBookList = (Collection<BookDTO>) cartMap.values();
		session.setAttribute("MyCartItems", cartBookList);
		if (cartBookList.size() == 0) {
			request.setAttribute("CartEmptyMSG", "No Books In Cart - Please Continue Shoping");
		}
		return "showCart";
	}

	@GetMapping("/continueShopping")
	public String continueShopping() {
		return "redirect:/showAllBooks";
	}

	@PostMapping("/removeFromCart")
	public String removeBookFromCart(@RequestParam("bookId") String bookId, HttpSession session) {
		Object obj = session.getAttribute("MyCart");
		Map<Integer, BookDTO> cartMap = (Map<Integer, BookDTO>) obj;
		cartMap.remove(Integer.parseInt(bookId));
		return "redirect:/showMyCart";
	}

	@GetMapping("/placeOrder")
	public String placeMyOrder(HttpSession session) {
		if (mycartMap.size() > 0) {
			bookStoreService.placeOrder(mycartMap);
			mycartMap.clear();
		}
		return "orderSuccess";
	}

	@GetMapping("/showRatingsForm")
	public String showRatingsForm(Model model) {
		UserRatingDTO userRatingDTO = new UserRatingDTO();
		userRatingDTO.setUserId("U-12");
		model.addAttribute("myUserRating", userRatingDTO);
		return "addRating";
	}

	@PostMapping("/addMyRating")
	public String addMyrating(@ModelAttribute("myUserRating") UserRatingDTO userRating) {
		bookStoreService.addUserRating(userRating);
		return "ratingSuccess";
	}

	@GetMapping("/showMyRatings")
	public String showMyRatingsList(Model model, HttpSession session) {
		List<UserRatingDTO> userRatingsList = bookStoreService.getMyRatings("U-12");
		session.setAttribute("MyUserRatingList", userRatingsList);
		return "ratingList";
	}

}
