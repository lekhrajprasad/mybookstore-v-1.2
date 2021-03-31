package com.lpras.booksearch.dao;

import com.lpras.booksearch.entity.BookInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInventoryDAO extends JpaRepository<BookInventory, Integer> {
}
