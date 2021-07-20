package com.termgrid.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TermgridBookStoreApplication {

	/*
		1. Create a simple book rating site
		2. entity :- User, Book
		3. User can upload book name, images and Url
		4. Other users can rate 5stars and add comments like the way we see on Amazon.com
		5. Viewers can see ratings and read comments, sort by latest or highest rated etc
	 */
	public static void main(String[] args) {
		SpringApplication.run(TermgridBookStoreApplication.class, args);
	}
}
