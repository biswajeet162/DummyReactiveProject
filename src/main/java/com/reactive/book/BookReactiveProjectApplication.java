package com.reactive.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.reactive.book.controller.BookWebClient;

@SpringBootApplication
public class BookReactiveProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookReactiveProjectApplication.class, args);

//		BookWebClient bwc = new BookWebClient();
//		bwc.getAllBooksDemo();
//		bwc.getBookByIdDemo();
//		bwc.addBookDemo();
//		bwc.updateBookDemo();
//		bwc.deleteBookByIdDemo();

	}

}
