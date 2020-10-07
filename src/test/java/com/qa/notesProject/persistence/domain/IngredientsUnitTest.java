package com.qa.notesProject.persistence.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
@SpringBootTest
class IngredientsUnitTest {
	@Autowired
	private Ingredients ingredient;
	
	@MockBean
	private Ingredients ingredientDouble;
	
	private String name, foodGroup;
	private Double price, weight;
	private Recipe recipe;
	private Long id;
	
	
	
	
	@BeforeEach
	void init() {
		name = "Pasta";
		foodGroup = "Carbs";
		price = 1.2;
		weight = 500.0;
		ingredientDouble.setName(name);
		
		
	}
	
	@Test
	void equals() {
		when(ingredient.getName()).thenReturn(name);
		when(ingredient.getFoodGroup()).thenReturn(foodGroup);
		System.out.println(ingredient.getName());
		assertThat(false).isEqualTo(ingredient.equals(ingredientDouble));
	}

}
