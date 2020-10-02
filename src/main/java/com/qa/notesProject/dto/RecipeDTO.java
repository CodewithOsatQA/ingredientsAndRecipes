package com.qa.notesProject.dto;

import java.util.ArrayList;
import java.util.List;
import com.qa.notesProject.persistence.domain.Ingredients;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class RecipeDTO {

	private Long id;
	

	private String name;
	

	private String difficulty;
	
	
    private List<Ingredients> ingredients = new ArrayList<>();
}
