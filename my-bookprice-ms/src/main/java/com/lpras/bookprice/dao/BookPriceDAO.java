package com.lpras.bookprice.dao;


import com.lpras.bookprice.entity.BookPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookPriceDAO extends JpaRepository<BookPrice, Integer> {
}
