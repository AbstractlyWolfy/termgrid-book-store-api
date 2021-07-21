package com.termgrid.bookstore.controller.request.books;

public class BookCreateRequest {

    private String name;
    private String description;
    private String author;
    private String image;

    /**
     * Get the book name
     * @return name - {@link String}
     */
    public String getName() {
        return name;
    }


    /**
     * Get the book description
     * @return description - {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the book author
     * @return author - {@link com.termgrid.bookstore.model.User}
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get the Base64 image associated with this book
     * @return image - {@link String}
     */
    public String getImage() {
        return image;
    }
}
