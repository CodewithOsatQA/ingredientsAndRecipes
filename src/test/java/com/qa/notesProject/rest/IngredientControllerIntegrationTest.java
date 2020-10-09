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
import com.qa.notesProject.dto.IngredientDTO;
import com.qa.notesProject.persistence.domain.Ingredients;
import com.qa.notesProject.persistence.repository.IngredientRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class IngredientControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private IngredientRepository repo;
    @Autowired
    private ModelMapper modelMapper;

    private IngredientDTO mapToDTO(Ingredients ingredient) {
        return this.modelMapper.map(ingredient, IngredientDTO.class);
    }
    @Autowired
    private ObjectMapper objectMapper;

    private Ingredients testIngredient;
    private Ingredients testIngredientWithId;
    private IngredientDTO ingredientDTO;

    private Long id;

    @BeforeEach
    void init() {
        this.repo.deleteAll();

        this.testIngredient = new Ingredients("Pasta","Carbohydrate",12.3,128.1);
        this.testIngredientWithId = this.repo.save(this.testIngredient);
        this.ingredientDTO = this.mapToDTO(this.testIngredientWithId);
        this.id = this.testIngredientWithId.getId();
    }
    @Test
    void testDelete() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/ingredient/delete/" + this.id)).andExpect(status().isNoContent());
    }
    
    @Test
    void testReadAll() throws Exception {
        List<IngredientDTO> ingredientList = new ArrayList<>();
        ingredientList.add(this.ingredientDTO);
        String expected = this.objectMapper.writeValueAsString(ingredientList);
      

        String actual = this.mock.perform(request(HttpMethod.GET, "/ingredient/read").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(expected, actual);
    }

    @Test
    void testCreate() throws Exception {
        this.mock
                .perform(request(HttpMethod.POST, "/ingredient/create").contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.testIngredient))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(this.objectMapper.writeValueAsString(this.ingredientDTO)));
    }

    @Test
    void testRead() throws Exception {
        this.mock.perform(request(HttpMethod.GET, "/ingredient/read/" + this.id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(this.ingredientDTO)));
    }

    

    @Test
    void testUpdate() throws Exception {
        IngredientDTO newIngredient = new IngredientDTO(null,"Pasta","Carbohydrate",12.3,128.1);
        Ingredients updatedIngredient = new Ingredients(newIngredient.getName(), newIngredient.getFoodGroup(),
                newIngredient.getPrice(),newIngredient.getWeight());
        updatedIngredient.setId(this.id);
        String expected = this.objectMapper.writeValueAsString(this.mapToDTO(updatedIngredient));

        String actual = this.mock.perform(request(HttpMethod.PUT, "/ingredient/update/" + this.id)
                .contentType(MediaType.APPLICATION_JSON).content(this.objectMapper.writeValueAsString(newIngredient))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted())
                .andReturn().getResponse().getContentAsString();

        assertEquals(expected, actual);
    }

   

}
