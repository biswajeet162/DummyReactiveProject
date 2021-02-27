package com.reactive.book.model;

public class Book {
	private int id;
	private String name;

	public Book() {
	}

	public Book(String name) {
		this.name = name;
	}

	public Book(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getId() + ", " + getName();
	}
}