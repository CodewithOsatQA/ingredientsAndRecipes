package com.qa.notesProject.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.notesProject.dto.RecipeDTO;
import com.qa.notesProject.persistence.domain.Recipe;
import com.qa.notesProject.persistence.repository.RecipeRepository;




@SpringBootTest
class RecipeServiceUnitTest {

   
    @Autowired
    private RecipeService service;

    @MockBean
    private RecipeRepository repo;


    @MockBean
    private ModelMapper modelMapper;

    private List<Recipe> recipeList;
    private Recipe testRecipe;
    private Recipe testRecipeWithId;
    private RecipeDTO recipeDTO;

    final Long id = 1L;
    
    final String recipeName = "Pasta Bake";
    final String difficulty = "hard";
    @BeforeEach
    void init() {
        this.recipeList = new ArrayList<>();
        this.testRecipe = new Recipe(recipeName, difficulty);
        this.recipeList.add(testRecipe);
        this.testRecipeWithId = new Recipe(testRecipe.getName(), testRecipe.getDifficulty());
        this.testRecipeWithId.setId(id);
        this.recipeDTO = modelMapper.map(testRecipeWithId, RecipeDTO.class);
    }

    @Test
    void createTest() {

        when(this.repo.save(this.testRecipe)).thenReturn(this.testRecipeWithId);

    
        when(this.modelMapper.map(this.testRecipeWithId, RecipeDTO.class)).thenReturn(this.recipeDTO);



        RecipeDTO expected = this.recipeDTO;
        RecipeDTO actual = this.service.create(this.testRecipe);
        assertThat(expected).isEqualTo(actual);


        verify(this.repo, times(1)).save(this.testRecipe);
    }

    @Test
    void readTest() {

        when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testRecipeWithId));

        when(this.modelMapper.map(testRecipeWithId, RecipeDTO.class)).thenReturn(recipeDTO);

        assertThat(this.recipeDTO).isEqualTo(this.service.read(this.id));

        verify(this.repo, times(1)).findById(this.id);
    }

    @Test
    void readAllTest() {

        when(this.repo.findAll()).thenReturn(this.recipeList);

        when(this.modelMapper.map(this.testRecipeWithId, RecipeDTO.class)).thenReturn(recipeDTO);

        assertThat(this.service.read().isEmpty()).isFalse();

        verify(this.repo, times(1)).findAll();
    }

    @Test
    void updateTest() {
        Recipe recipe = new Recipe(recipeName, difficulty);
        recipe.setId(this.id);

        RecipeDTO recipeDTO = new RecipeDTO(null, recipeName, difficulty, null);

        Recipe updatedRecipe = new Recipe(recipeDTO.getName(), recipeDTO.getDifficulty());
        updatedRecipe.setId(this.id);

        RecipeDTO updatedRecipeDTO = new RecipeDTO(this.id, updatedRecipe.getName(),
                updatedRecipe.getDifficulty(), null);


        when(this.repo.findById(this.id)).thenReturn(Optional.of(recipe));


        when(this.repo.save(recipe)).thenReturn(updatedRecipe);

        when(this.modelMapper.map(updatedRecipe, RecipeDTO.class)).thenReturn(updatedRecipeDTO);

        assertThat(updatedRecipeDTO).isEqualTo(this.service.update(recipeDTO, this.id));


        verify(this.repo, times(1)).findById(1L);
        verify(this.repo, times(1)).save(updatedRecipe);
    }

    @Test
    void deleteTest() {

        when(this.repo.existsById(id)).thenReturn(true, false);

        assertThat(this.service.delete(id)).isTrue();

        verify(this.repo, times(1)).deleteById(id);

        verify(this.repo, times(2)).existsById(id);
    }

}