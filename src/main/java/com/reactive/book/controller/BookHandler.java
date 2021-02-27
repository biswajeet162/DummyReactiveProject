package com.reactive.book.controller;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.book.model.Book;
import com.reactive.book.service.BookService;

import reactor.core.publisher.Mono;
@Component
public class BookHandler {
	@Autowired
	private BookService bookService;
	
	public Mono<ServerResponse> getAllBooks(ServerRequest request) {
		System.out.println("\n\n\nCalled --->  BookHandler  --->  getAllBooks  \n\n\n");//
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			.body(bookService.getAllBooks(), Book.class);
	}
	public Mono<ServerResponse> getBookById(ServerRequest request) {
		int id = Integer.parseInt(request.pathVariable("id"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			.body(bookService.getBookById(id), Book.class);
	}	
	public Mono<ServerResponse> addBook(ServerRequest request) {
		return request.bodyToMono(Book.class)
				.flatMap(book -> bookService.addBook(book))
				.flatMap(newBook -> ServerResponse.created(URI.create("/books/" + newBook.getId()))
						.contentType(MediaType.APPLICATION_JSON)
						.build());
	}
	public Mono<ServerResponse> updateBook(ServerRequest request) {
		return request.bodyToMono(Book.class)
				.flatMap(book -> bookService.updateBook(book))
				.flatMap(modBook -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(BodyInserters.fromValue(modBook)));
	}	
	public Mono<ServerResponse> deleteBookById(ServerRequest request) {
		return bookService.deleteBookById(Integer.parseInt(request.pathVariable("id")))
				.flatMap(val -> {
					if (val == true) {
					    return ServerResponse.noContent().build();
					}
					return ServerResponse.notFound().build();
				});
	}	
} 