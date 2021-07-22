package com.termgrid.bookstore.controller.request.books;

import com.termgrid.bookstore.model.User;

public class ReviewCreateRequest {

    private int bookId;
    private String description;
    private int rating;

    public int getBookId() {
        return bookId;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }
}
