package com.qa.notesProject.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.notesProject.dto.IngredientDTO;
import com.qa.notesProject.persistence.domain.Ingredients;
import com.qa.notesProject.service.IngredientService;

@SpringBootTest
class IngredientControllerUnitTest {
	
	@Autowired
	private IngredientController controller;
	
	
	@MockBean
	private IngredientService service;
	
	@Autowired
	private ModelMapper mapper;
	
	
	private IngredientDTO mapToDTO(Ingredients ingredient) {
		return this.mapper.map(ingredient, IngredientDTO.class);
	}
	private List<Ingredients> listOfIngredients;
	private Ingredients pasta;
	private Ingredients spaghetti;
	private IngredientDTO ingredientDTO;
	private final Long id = 1L;
	private final String name = "Pasta";
	private final String foodGroup = "Carbohydrate";
	private final Double price = 0.5;
	private final Double weight = 500.0;
	
	@BeforeEach
	void initialise() {
		listOfIngredients = new ArrayList<>();
		pasta = new Ingredients(name,foodGroup,price,weight);
		spaghetti = new Ingredients(pasta.getName(), pasta.getFoodGroup(), pasta.getPrice(),pasta.getWeight());
		spaghetti.setId(id);
		listOfIngredients.add(spaghetti);
		ingredientDTO = mapToDTO(spaghetti);
		
	}
	
	@Test
	void testCreate() {
		when(this.service.create(pasta)).thenReturn(this.ingredientDTO);
		IngredientDTO testIngredient = ingredientDTO;
		assertThat(new ResponseEntity<IngredientDTO>(testIngredient, HttpStatus.CREATED)).isEqualTo(controller.create(pasta));
		verify(this.service, times(1)).create(pasta);
	}
	
}
