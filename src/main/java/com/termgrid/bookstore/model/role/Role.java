package com.termgrid.bookstore.model.role;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "name")
    @NonNull
    private String name;

    /**
     * Get the unique id for this role
     * @return id - {@link Integer}
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get role name
     * @return name - {@link String}
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Set the role name.
     * @param name - {@link String}
     */
    public void setName(@NonNull String name) {
        this.name = name;
    }
}
