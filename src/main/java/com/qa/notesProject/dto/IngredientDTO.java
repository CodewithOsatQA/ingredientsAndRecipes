package com.qa.notesProject.dto;

import com.qa.notesProject.persistence.domain.Recipe;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class IngredientDTO {
	
	private Long id;
	private String name;
	private String foodGroup;
	private Double price;
	private Double weight;

	
	
}