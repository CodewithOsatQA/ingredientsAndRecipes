package com.qa.notesProject.rest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.notesProject.dto.RecipeDTO;
import com.qa.notesProject.persistence.domain.Recipe;

import com.qa.notesProject.persistence.repository.RecipeRepository;


@SpringBootTest
@AutoConfigureMockMvc
class RecipeControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private RecipeRepository repo;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private Long id;
    private Recipe testRecipe;
    private Recipe testRecipeWithId;
    
    private RecipeDTO mapToDTO(Recipe recipe) {
        return this.modelMapper.map(recipe, RecipeDTO.class);
    }

    @BeforeEach
    void init() {
        this.repo.deleteAll();
        this.testRecipe = new Recipe("Pasta","Easy");
        this.testRecipeWithId = this.repo.save(this.testRecipe);
        this.id = this.testRecipeWithId.getId();
    }

    @Test
    void testCreate() throws Exception {
        this.mock
            .perform(request(HttpMethod.POST, "/recipe/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(testRecipe))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().json(this.objectMapper.writeValueAsString(testRecipeWithId)));
    }

    @Test
    void testRead() throws Exception {
        this.mock
            .perform(request(HttpMethod.GET, "/recipe/read/" + this.id)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(this.objectMapper.writeValueAsString(this.testRecipe)));
    }

    @Test
    void testReadAll() throws Exception {
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(this.testRecipeWithId);

        String content = this.mock
            .perform(request(HttpMethod.GET, "/recipe/read")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
        
        assertEquals(this.objectMapper.writeValueAsString(recipeList), content);
    }

    @Test
    void testUpdate() throws Exception {
        Recipe newRecipe = new Recipe("Spaghetti","hard");
        Recipe updatedRecipe = new Recipe(newRecipe.getName(), newRecipe.getDifficulty());
        updatedRecipe.setId(this.id);

        String result = this.mock
            .perform(request(HttpMethod.PUT, "/recipe/update/" + this.id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(newRecipe)))
            .andExpect(status().isAccepted())
            .andReturn().getResponse().getContentAsString();
        
        assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updatedRecipe)), result);
    }
    
    @Test
    void testDelete() throws Exception {
        this.mock
            .perform(request(HttpMethod.DELETE, "/recipe/delete/" + this.id))
            .andExpect(status().isNoContent());
    }

}
