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

import com.qa.notesProject.dto.RecipeDTO;
import com.qa.notesProject.persistence.domain.Recipe;
import com.qa.notesProject.service.RecipeService;




@SpringBootTest
class RecipeControllerUnitTest {
	
	@Autowired
	private RecipeController controller;
	
	
	@MockBean
	private RecipeService service;
	
	@Autowired
	private ModelMapper mapper;
	
	
	private RecipeDTO mapToDTO(Recipe recipe) {
		return this.mapper.map(recipe, RecipeDTO.class);
	}
	private List<Recipe> recipeList;
	private Recipe pastaBake;
	private Recipe pastaBaked;
	private RecipeDTO recipeDTO;
	private final Long id = 1L;
	private final String name = "Pasta Bake";
	private final String difficulty = "Hard";

	
	@BeforeEach
	void initialise() {
		recipeList = new ArrayList<>();
		pastaBake = new Recipe(name, difficulty);
		pastaBaked = new Recipe(pastaBake.getName(), pastaBake.getDifficulty());
		pastaBaked.setId(id);
		recipeList.add(pastaBaked);
		recipeDTO = mapToDTO(pastaBaked);
		
	}
	
	@Test
	void testCreate() {
		when(this.service.create(pastaBake)).thenReturn(this.recipeDTO);
		RecipeDTO newRecipe = recipeDTO;
		assertThat(new ResponseEntity<RecipeDTO>(newRecipe, HttpStatus.CREATED)).isEqualTo(controller.create(pastaBake));
		verify(this.service, times(1)).create(pastaBake);
	}
	
	@Test
	void testRead() {
		when(this.service.read(this.id)).thenReturn(recipeDTO);
		
		RecipeDTO newRecipeDTO = this.recipeDTO;
		assertThat(new ResponseEntity<RecipeDTO>(newRecipeDTO, HttpStatus.OK)).isEqualTo(this.controller.read(this.id));
	}
	
	 @Test
	  void readAllTest() {
	        when(this.service.read())
	                .thenReturn(this.recipeList.stream().map(this::mapToDTO).collect(Collectors.toList()));
	        assertThat(this.controller.read().getBody().isEmpty()).isFalse();

	        
	 }
	 @Test
	  void updateTest() {
	        RecipeDTO recipeNew = new RecipeDTO(null, name, difficulty, null);
	        RecipeDTO recipeWithNew = new RecipeDTO(this.id, recipeNew.getName(), recipeNew
	        		.getDifficulty(),
	                recipeNew.getIngredients());

	        
	        when(this.service.update(recipeNew, this.id)).thenReturn(recipeWithNew);

	        assertThat(new ResponseEntity<RecipeDTO>(recipeWithNew, HttpStatus.ACCEPTED))
	                .isEqualTo(this.controller.update(this.id, recipeNew));

	        verify(this.service, times(1)).update(recipeNew, this.id);
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
