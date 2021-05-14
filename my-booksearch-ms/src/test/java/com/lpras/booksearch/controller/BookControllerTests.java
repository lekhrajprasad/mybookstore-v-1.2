package com.lpras.booksearch.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.lpras.booksearch.entity.Book;
import com.lpras.booksearch.service.BookService;

@WebMvcTest(BookController.class)
public class BookControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@Test
	public void testGetBooks() {

		List<Book> books = new ArrayList<>();
		books.add(new Book(101, "Spring", "lekh", "ISBN", "Java"));
		books.add(new Book(102, "Sprin2", "lekh2", "ISBN2", "Java2"));

		Mockito.when(bookService.getBooks("lekh", "Java")).thenReturn(books);

		String uri = "/mybooks/{author}/{category}";
		try {
			mockMvc.perform(get(uri)).andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//@Test
	public void testGetBookById() {

		String uri = "/showAllBooks";
		try {
			mockMvc.perform(get(uri)).andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
