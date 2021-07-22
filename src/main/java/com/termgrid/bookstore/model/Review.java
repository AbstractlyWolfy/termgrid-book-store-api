package com.termgrid.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public final class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnore
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    @JsonIgnore
    private Book book;

    /** The description for this review */
    @Column(name = "description")
    private String description;

    /** The rating associated with this review */
    @Column(name = "rating")
    private int rating;

    @Column(name = "date_created")
    private Date created;

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
     * Set the book the review was left on
     * @param book - {@link Book}
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Get the description for this review
     * @return description - {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description for this review
     * @param description - {@link String}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the author for this review
     * @return author - {@link User}
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Set the author for this review
     * @param author - {@link User}
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Get the rating for this review
     * @return rating - {@link Integer}
     */
    public int getRating() {
        return rating;
    }

    /**
     * Set the rating for this review
     * @param rating - {@link Integer}
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Get the date this review was created
     * @return created - {@link Date}
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Set the date the review was created
     * @param created - {@link Date}
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}
