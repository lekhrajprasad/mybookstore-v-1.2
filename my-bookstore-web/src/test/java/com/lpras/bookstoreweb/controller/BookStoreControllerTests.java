/*
package com.lpras.bookstoreweb.controller;

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

import com.lpras.bookstoreweb.service.BookStoreService;
import com.lpras.dto.BookDTO;

@WebMvcTest(BookStoreController.class)
public class BookStoreControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookStoreService bookStoreService;
	
	@Test
	public void testShowBooksList(){
		
		List<BookDTO> bookDTOs = new ArrayList<>(); 
		BookDTO bookDTO = new BookDTO();
		bookDTO.setAuthor("lekh");
		bookDTO.setBookId(101);
		bookDTO.setBookName("Spring");
		bookDTO.setCategory("Java");
		bookDTO.setPublications("Lpras");
		
		bookDTOs.add(bookDTO);
		Mockito.when(bookStoreService.getMyBooks("lekh", "Java")).thenReturn(bookDTOs);
		
		String uri = "/showAllBooks";
		try {
			mockMvc.perform(get(uri)).andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
*/
