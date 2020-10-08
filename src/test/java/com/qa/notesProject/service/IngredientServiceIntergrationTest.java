package com.qa.notesProject.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.notesProject.dto.IngredientDTO;
import com.qa.notesProject.persistence.domain.Ingredients;
import com.qa.notesProject.persistence.repository.IngredientRepository;



@SpringBootTest
class IngredientServiceIntegrationTest {

    // because we're testing the service layer, we can't use a MockMvc
    // because MockMvc only models a controller (in mockito format)

    @Autowired
    private IngredientService service;

    @Autowired
    private IngredientRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    private IngredientDTO mapToDTO(Ingredients ingredient) {
        return this.modelMapper.map(ingredient, IngredientDTO.class);
    }

    // there's no objectMapper this time
    // because we don't need to convert any returned objects to JSON
    // that's a controller job, and we're not testing the controller! :D

    private Ingredients testIngredient;
    private Ingredients testIngredientWithId;
    private IngredientDTO testIngredientDTO;

    private Long id;
    private final String foodName = "Pasta";
    final String foodCategory = "Carbohydrate";
    final Double foodPrice = 12.3;
    final Double foodWeight = 300.0;;

    @BeforeEach
    void init() {
        this.repo.deleteAll();
        this.testIngredient = new Ingredients(foodName, foodCategory, foodPrice, foodWeight);
        this.testIngredientWithId = this.repo.save(this.testIngredient);
        this.testIngredientDTO = this.mapToDTO(testIngredientWithId);
        this.id = this.testIngredientWithId.getId();
    }

    @Test
    void testCreate() {
        assertThat(this.testIngredientDTO)
            .isEqualTo(this.service.create(testIngredient));
    }

    @Test
    void testRead() {
        assertThat(this.testIngredientDTO)
                .isEqualTo(this.service.read(this.id));
    }

    @Test
    void testReadAll() {
        // check this one out with a breakpoint and running it in debug mode
        // so you can see the stream happening
        assertThat(this.service.read())
                .isEqualTo(Stream.of(this.testIngredientDTO)
                        .collect(Collectors.toList()));
    }

    @Test
    void testUpdate() {
        IngredientDTO newIngredient = new IngredientDTO(null, foodName, foodCategory, foodPrice, foodWeight);
        IngredientDTO updatedIngredient =
                new IngredientDTO(this.id, newIngredient.getName(), newIngredient.getFoodGroup(), 
                		newIngredient.getPrice(), newIngredient.getWeight());

        assertThat(updatedIngredient)
            .isEqualTo(this.service.update(newIngredient, this.id));
    }

    @Test
    void testDelete() {
        assertThat(this.service.delete(this.id)).isTrue();
    }

}
