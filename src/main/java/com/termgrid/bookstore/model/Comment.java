package com.termgrid.bookstore.model;

import javax.persistence.*;

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
     * Get the comment that was left on a review
     * @return comment - {@link String}
     */
    public String getComment() {
        return comment;
    }

    /**
     * Get the author for this review
     * @return author - {@link User}
     */
    public User getAuthor() {
        return author;
    }
}
