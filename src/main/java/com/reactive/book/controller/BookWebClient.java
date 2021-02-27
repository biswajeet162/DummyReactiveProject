package com.reactive.book.controller;

import org.springframework.web.reactive.function.client.WebClient;

import com.reactive.book.model.Book;

public class BookWebClient {
	private WebClient client = WebClient.create("http://localhost:8200");
	
	
	public void getAllBooksDemo() {
		client.get().uri("/books").exchange().flatMapMany(res -> res.bodyToFlux(Book.class)).collectList()
				.subscribe(books -> books.forEach(b -> System.out.println(b)));
	}

	public void getBookByIdDemo() {
		int id = 201;
		client.get().uri("/books/" + id).exchange().flatMap(res -> res.bodyToMono(Book.class))
				.subscribe(book -> System.out.println("GET: " + book), err -> System.out.println(err.getMessage()));
	}

	public void addBookDemo() {
		client.post().uri("/add").bodyValue(new Book("Spring")).exchange().subscribe(res -> System.out
				.println("POST: " + res.statusCode() + ", " + res.headers().asHttpHeaders().getLocation()));
	}

	public void updateBookDemo() {
		client.put().uri("/update").bodyValue(new Book(103, "Android")).exchange()
				.flatMap(res -> res.bodyToMono(Book.class)).subscribe(book -> System.out.println("PUT: " + book));
	}

	public void deleteBookByIdDemo() {
		int id = 104;
		client.delete().uri("/books/" + id).exchange()
				.subscribe(res -> System.out.println("DELETE: " + res.statusCode()));
	}
}