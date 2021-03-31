package com.lpras.bookprice.controller;

import com.lpras.bookprice.entity.BookPrice;
import com.lpras.bookprice.service.BookPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class BookPriceController {
    static Logger log = LoggerFactory.getLogger(BookPriceController.class);
    @Autowired
    BookPriceService bookPriceService;
    @Value(value = "${server.port}")
    String serverPort;
    @GetMapping(value = "/bookPrice/{bookId}")
    public BookPrice getBookPrice(@PathVariable("bookId") Integer bookId) {
        log.info("--BookPriceController--getBookPrice()--");
        System.out.println("---BookPriceController---getBookPrice()----: " + serverPort);
        BookPrice bookPrice = bookPriceService.getBookPriceById(bookId);
        return bookPrice;
    }

    @GetMapping(value = "/offeredPrice/{bookId}")
    public double getOfferedPrice(@PathVariable("bookId") Integer bookId) {
        log.info("--BookPriceController--getOfferedPrice()--");
        System.out.println("---BookPriceController---getBookPrice()----: " + serverPort);
        double offeredPrice = bookPriceService.getOfferedPriceById(bookId);
        return offeredPrice;
    }

}
