package com.qa.notesProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class NotesProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesProjectApplication.class, args);
	}

}
