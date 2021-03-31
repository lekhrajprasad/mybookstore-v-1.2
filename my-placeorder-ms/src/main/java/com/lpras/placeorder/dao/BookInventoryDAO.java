package com.lpras.placeorder.dao;

import com.lpras.placeorder.entity.BookInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInventoryDAO extends JpaRepository<BookInventory, Integer> {
}
