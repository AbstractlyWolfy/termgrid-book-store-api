package com.termgrid.bookstore.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public final class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;

    /** The name of the book */
    @Column(name = "name", unique = true)
    private String name;

    /** The slug for the page url */
    @Column(name = "slug", unique = true)
    private String slug;

    /** The descriptuon for the book */
    @Column(name = "description", length = 512)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @Lob
    private byte[] image;

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

    /**
     * Get the description for the book
     * @return description - {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the book description
     * @param description - {@link String}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the author for the book
     * @return author - {@link User}
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Set the book author
     * @param author - {@link User}
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Get the category for the book
     * @return category - {@link Category}
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Get the image for the book
     * @return image - {@link Byte[]}
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Set the image for this book
     * @param image - Byte[]
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * Get the date this book was created
     * @return created - {@link Date}
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Set the date the book was created
     * @param created - {@link Date}
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}
