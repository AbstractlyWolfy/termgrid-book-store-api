package com.termgrid.bookstore.model;

import javax.persistence.*;

@Entity
@Table
public final class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;

    @Column(name = "name", length = 30)
    private String name;

    /** The slug for the page url */
    @Column(name = "slug", unique = true)
    private String slug;

    /**
     * Get the id for the book
     * @return id - {@link Integer}
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get the slug for the book
     * @return slug - {@link String}
     */
    public String getSlug() {
        return slug;
    }
}
