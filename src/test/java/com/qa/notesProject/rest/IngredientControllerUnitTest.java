package com.qa.notesProject.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	@Test
	void testRead() {
		when(this.service.read(this.id)).thenReturn(ingredientDTO);
		
		IngredientDTO testIngredient = this.ingredientDTO;
		assertThat(new ResponseEntity<IngredientDTO>(testIngredient, HttpStatus.OK)).isEqualTo(this.controller.read(this.id));
	}
	
	 @Test
	  void readAllTest() {
	        when(this.service.read())
	                .thenReturn(this.listOfIngredients.stream().map(this::mapToDTO).collect(Collectors.toList()));
	        assertThat(this.controller.read().getBody().isEmpty()).isFalse();

	        
	 }
	 @Test
	  void updateTest() {
	        IngredientDTO ingredient = new IngredientDTO(null,name, foodGroup, price, weight);
	        IngredientDTO newIngrdient = new IngredientDTO(this.id, ingredient.getName(), ingredient.getFoodGroup(),
	                ingredient.getPrice(), ingredient.getWeight());

	        
	        when(this.service.update(ingredient, this.id)).thenReturn(newIngrdient);

	        assertThat(new ResponseEntity<IngredientDTO>(newIngrdient, HttpStatus.ACCEPTED))
	                .isEqualTo(this.controller.update(this.id, ingredient));

	        verify(this.service, times(1)).update(ingredient, this.id);
	    }

	    
	    @Test
	    void deleteTest() {
	        this.controller.delete(id);
	        verify(this.service, times(1)).delete(id);
	        
	    }
//	    @Test
//	    void deleteTest2() {
//	        //this.controller.delete(2L);
//	        when(this.service.delete(2L)).thenReturn(false);
//	        assertThat(new ResponseEntity<IngredientDTO>(HttpStatus.NO_CONTENT))
//            .isEqualTo(this.controller.delete(2L));
//	        verify(this.service, times(1)).delete(2L);
//	        
//	    }
}
