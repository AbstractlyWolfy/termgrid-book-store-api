package com.termgrid.bookstore.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public final class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", referencedColumnName = "review_id")
    private Review review;

    /** The description for this review */
    @Column(name = "comment")
    private String comment;

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
     * Get the review that the comment was left on
     * @return review - {@link Review}
     */
    public Review getReview() {
        return review;
    }

    /**
     * Set the review the comment was left on
     * @param review - review
     */
    public void setReview(Review review) {
        this.review = review;
    }

    /**
     * Get the comment that was left on a review
     * @return comment - {@link String}
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set the comment that was left on this review
     * @param comment - {@link String}
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Get the author for this review
     * @return author - {@link User}
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Set the author who wrote this comment
     * @param author - {@link User}
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Get the date this user was comment
     * @return created - {@link Date}
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Set the date the comment was created
     * @param created - {@link Date}
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}
