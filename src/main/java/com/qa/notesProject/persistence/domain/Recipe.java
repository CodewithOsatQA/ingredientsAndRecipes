package com.qa.notesProject.persistence.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
//@ToString
//@EqualsAndHashCode
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String difficulty;
	
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Ingredients> ingredients = new ArrayList<>();

    public Recipe(String name, String difficulty) {
    	super();
       this.name = name;
       this.difficulty = difficulty;
    }


}
