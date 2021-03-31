package com.lpras.bookprice.service;

import com.lpras.bookprice.entity.BookPrice;

public interface BookPriceService {
    public BookPrice getBookPriceById(Integer bookId);
    public double getOfferedPriceById(Integer bookId);
}
