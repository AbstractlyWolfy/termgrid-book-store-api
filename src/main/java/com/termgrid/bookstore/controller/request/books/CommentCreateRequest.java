package com.termgrid.bookstore.controller.request.books;

public class CommentCreateRequest {

    private int reviewId;
    private String comment;

    public int getReviewId() {
        return reviewId;
    }

    public String getComment() {
        return comment;
    }
}
