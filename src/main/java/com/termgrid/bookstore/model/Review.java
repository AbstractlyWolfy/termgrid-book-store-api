package com.termgrid.bookstore.model;

import javax.persistence.*;

@Entity
@Table
public final class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    /** The description for this review */
    @Column(name = "description")
    private String description;

    /** The rating associated with this review */
    @Column(name = "rating")
    private int rating;

    /**
     * Get the id for the book
     * @return id - {@link Integer}
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get the book this review was left on
     * @return book - {@link Book}
     */
    public Book getBook() {
        return book;
    }

    /**
     * Get the description for this review
     * @return description - {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the author for this review
     * @return author - {@link User}
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Get the rating for this review
     * @return rating - {@link Integer}
     */
    public int getRating() {
        return rating;
    }
}
