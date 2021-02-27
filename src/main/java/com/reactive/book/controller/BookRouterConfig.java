package com.reactive.book.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
@Configuration
@EnableWebFlux
@ComponentScan("com.concretepage")
public class BookRouterConfig {
	@Bean
	public RouterFunction<ServerResponse> root(BookHandler bookHandler) {
		return RouterFunctions.route()
		  .GET("/books", bookHandler::getAllBooks)
		  .GET("/books/{id}", RequestPredicates.accept(MediaType.TEXT_PLAIN), bookHandler::getBookById)
		  .POST("/add", RequestPredicates.contentType(MediaType.APPLICATION_JSON), bookHandler::addBook)
		  .PUT("/update", RequestPredicates.contentType(MediaType.APPLICATION_JSON), bookHandler::updateBook)
		  .DELETE("/books/{id}", RequestPredicates.accept(MediaType.TEXT_PLAIN), bookHandler::deleteBookById)
		  .build();
	}
} 