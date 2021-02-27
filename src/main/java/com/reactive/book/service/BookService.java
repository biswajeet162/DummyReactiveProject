package com.reactive.book.service;

import java.time.Duration;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

import com.reactive.book.model.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class BookService {
	public Flux<Book> getAllBooks() {
		return Flux.fromStream(Stream.of(
				new Book(201, "Python"),
				new Book(202, "HTML"),
				new Book(456, "Python"),
				new Book(5466, "fgh"),
				new Book(456, "hfdgh"),
				new Book(789, "fgh"),
				new Book(546, "gfhgfh"),
				new Book(87, "fghgfhfg")
				
				)).delayElements(Duration.ofSeconds(3)).log();
	}	
	public Mono<Book> getBookById(int id) {
		return Mono.just(new Book(id, "Java"));
	}	
	public Mono<Book> addBook(Book book) {
		return Mono.just(new Book(102, book.getName()));
	}
	public Mono<Book> updateBook(Book book) {
		return Mono.just(new Book(book.getId(), book.getName() +" - updated"));
	}	
	public Mono<Boolean> deleteBookById(int id) {
		return Mono.just(true);
	}	
} 