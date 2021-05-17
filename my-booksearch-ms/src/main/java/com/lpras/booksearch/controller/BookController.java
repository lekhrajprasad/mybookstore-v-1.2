package com.lpras.booksearch.controller;

import com.lpras.booksearch.dto.BookInfo;
import com.lpras.booksearch.entity.Book;
import com.lpras.booksearch.entity.BookInventory;
import com.lpras.booksearch.entity.BookRating;
import com.lpras.booksearch.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RefreshScope
public class BookController {
    static Logger log = LoggerFactory.getLogger(BookController.class);
    
    @Autowired
    private BookService bookService;
    
    @Value("${server.port}:8000")
    String port;
    
    @Value("${booksearch.cloudconfig.msgupdated:defaultMsg}")
    String msg;
            
    
    @GetMapping(value = "/mybooks/{author}/{category}")
    public List<Book> getBooks(@PathVariable String author, @PathVariable String category){
        log.info("--BookController -- getBooks()---");
        System.out.println("=============================::port:::::"+port);
        System.out.println("=============================::msg:::::"+msg);
        System.out.println(author+"\t"+category);
        return bookService.getBooks(author, category);
    }

    @GetMapping(value = "/mybooks/{bookId}")
    public BookInfo getBookById(@PathVariable Integer bookId){
        log.info("--BookController -- getBookById()---");
        System.out.println("=============================::port:::::"+port);
        System.out.println("=============================::msg:::::"+msg);
        return bookService.getBookInfo(bookId);
    }

	/*
	 * @PutMapping(value = "updateBookRating") public void
	 * updateBookRating(@RequestBody BookRating bookRating){
	 * System.out.println("----BookController---updateBookRating()---");
	 * bookService.updateBookRating(bookRating); }
	 * 
	 * @PutMapping(value = "updateBookInventory") public void
	 * updateBookInventory(@RequestBody BookInventory bookInventory){
	 * System.out.println("---BookController --updateBookInventory()----");
	 * bookService.updateBookInventory(bookInventory); }
	 */
}
