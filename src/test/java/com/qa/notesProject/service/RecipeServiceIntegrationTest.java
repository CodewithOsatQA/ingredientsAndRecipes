package com.qa.notesProject.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.notesProject.dto.IngredientDTO;
import com.qa.notesProject.dto.RecipeDTO;

import com.qa.notesProject.persistence.domain.Recipe;
import com.qa.notesProject.persistence.repository.RecipeRepository;



@SpringBootTest
class RecipeServiceIntegrationTest {

    @Autowired
    private RecipeService service;

    @Autowired
    private RecipeRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    private RecipeDTO mapToDTO(Recipe recipe) {
        return this.modelMapper.map(recipe, RecipeDTO.class);
    }

    private Recipe testRecipe;
    private Recipe testRecipeWithId;
    private RecipeDTO testRecipeDTO;

    private Long id;
    private final String recipeName = "Pasta Bake";
    final String difficulty = "Easy";
   

    @BeforeEach
    void init() {
        this.repo.deleteAll();
        this.testRecipe  = new Recipe (recipeName, difficulty);
        this.testRecipeWithId = this.repo.save(this.testRecipe );
        this.testRecipeDTO = this.mapToDTO(testRecipeWithId);
        this.id = this.testRecipeWithId.getId();
    }

    @Test
    void testCreate() {
        assertThat(this.testRecipeDTO)
            .isEqualTo(this.service.create(testRecipe));
    }

    @Test
    void testRead() {
        assertThat(this.testRecipeDTO)
                .isEqualTo(this.service.read(this.id));
    }

    @Test
    void testReadAll() {

        assertThat(this.service.read())
                .isEqualTo(Stream.of(this.testRecipeDTO)
                        .collect(Collectors.toList()));
    }

    @Test
    void testUpdate() {
    	List<IngredientDTO> dummy = new ArrayList<>(); 
        RecipeDTO newRecipe = new RecipeDTO(null, recipeName, difficulty, dummy);
        RecipeDTO updatedRecipe  =
                new RecipeDTO(this.id, newRecipe.getName(), newRecipe.getDifficulty(), 
                		newRecipe.getIngredients());

        assertThat(updatedRecipe )
            .isEqualTo(this.service.update(newRecipe, this.id));
    }

    @Test
    void testDelete() {
        assertThat(this.service.delete(this.id)).isTrue();
    }

}
