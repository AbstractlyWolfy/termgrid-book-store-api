package com.termgrid.bookstore.model;

import javax.persistence.*;

@Entity
@Table
public final class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    /** The name of the book */
    @Column(name = "name")
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
     * Get the name for the bok
     * @return name - {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name for the book
     * @param name - {@link String}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the url slug for the book
     * @return slug - {@link String}
     */
    public String getSlug() {
        return slug;
    }

    /**
     * Set the slug for the book
     * @param slug - {@link String}
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }
}
