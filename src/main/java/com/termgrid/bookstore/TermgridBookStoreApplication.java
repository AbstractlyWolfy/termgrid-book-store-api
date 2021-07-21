package com.termgrid.bookstore;

import com.termgrid.bookstore.dao.RoleDAO;
import com.termgrid.bookstore.dao.UserDAO;
import com.termgrid.bookstore.model.role.Role;
import com.termgrid.bookstore.model.role.SiteRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Arrays;

@SpringBootApplication
public class TermgridBookStoreApplication {

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private UserDAO userDAO;

	/*
		1. Create a simple book rating site
		2. entity :- User, Book
		3. User can upload book name, images and Url
		4. Other users can rate 5stars and add comments like the way we see on Amazon.com
		5. Viewers can see ratings and read comments, sort by latest or highest rated etc
	 */
	public static void main(String[] args) {
		SpringApplication.run(TermgridBookStoreApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onStartup() {
		// Create default roles if they do not exist
		Arrays.stream(SiteRole.values()).forEach(role -> {
			if (roleDAO.existsByName(role.name())) {
				return;
			}
			Role newRole = new Role();
			newRole.setName(role.name());
			roleDAO.save(newRole);
		});
	}
}
