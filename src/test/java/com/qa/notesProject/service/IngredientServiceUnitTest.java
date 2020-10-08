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

import com.qa.notesProject.dto.IngredientDTO;
import com.qa.notesProject.persistence.domain.Ingredients;
import com.qa.notesProject.persistence.repository.IngredientRepository;


@SpringBootTest
class IngredientServiceUnitTest {

   
    @Autowired
    private IngredientService service;

    @MockBean
    private IngredientRepository repo;


    @MockBean
    private ModelMapper modelMapper;

    private List<Ingredients> ingredientList;
    private Ingredients testIngredient;
    private Ingredients testIngredientWithId;
    private IngredientDTO ingredientDTO;

    final Long id = 1L;
    final String foodName = "Pasta";
    final String foodCategory = "Carbohydrate";
    final Double foodPrice = 12.3;
    final Double foodWeight = 300.0;

    @BeforeEach
    void init() {
        this.ingredientList = new ArrayList<>();
        this.testIngredient = new Ingredients(foodName, foodCategory, foodPrice, foodWeight);
        this.ingredientList.add(testIngredient);
        this.testIngredientWithId = new Ingredients(testIngredient.getName(), testIngredient.getFoodGroup(),
                testIngredient.getPrice(), testIngredient.getWeight());
        this.testIngredientWithId.setId(id);
        this.ingredientDTO = modelMapper.map(testIngredientWithId, IngredientDTO.class);
    }

    @Test
    void createTest() {

        when(this.repo.save(this.testIngredient)).thenReturn(this.testIngredientWithId);

    
        when(this.modelMapper.map(this.testIngredientWithId, IngredientDTO.class)).thenReturn(this.ingredientDTO);



        IngredientDTO expected = this.ingredientDTO;
        IngredientDTO actual = this.service.create(this.testIngredient);
        assertThat(expected).isEqualTo(actual);


        verify(this.repo, times(1)).save(this.testIngredient);
    }

    @Test
    void readTest() {

        when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testIngredientWithId));

        when(this.modelMapper.map(testIngredientWithId, IngredientDTO.class)).thenReturn(ingredientDTO);

        assertThat(this.ingredientDTO).isEqualTo(this.service.read(this.id));

        verify(this.repo, times(1)).findById(this.id);
    }

    @Test
    void readAllTest() {

        when(this.repo.findAll()).thenReturn(this.ingredientList);

        when(this.modelMapper.map(this.testIngredientWithId, IngredientDTO.class)).thenReturn(ingredientDTO);

        assertThat(this.service.read().isEmpty()).isFalse();

        verify(this.repo, times(1)).findAll();
    }

    @Test
    void updateTest() {
        Ingredients ingredient = new Ingredients(foodName, foodCategory, foodPrice, foodWeight);
        ingredient.setId(this.id);

        IngredientDTO ingredientDTO = new IngredientDTO(null, foodName, foodCategory, foodPrice, foodWeight);

        Ingredients updatedIngredient = new Ingredients(ingredientDTO.getName(), ingredientDTO.getFoodGroup(),
                ingredientDTO.getPrice(), ingredientDTO.getWeight());
        updatedIngredient.setId(this.id);

        IngredientDTO updatedIngredientDTO = new IngredientDTO(this.id, updatedIngredient.getName(),
                updatedIngredient.getFoodGroup(), updatedIngredient.getPrice(), updatedIngredient.getWeight());


        when(this.repo.findById(this.id)).thenReturn(Optional.of(ingredient));


        when(this.repo.save(ingredient)).thenReturn(updatedIngredient);

        when(this.modelMapper.map(updatedIngredient, IngredientDTO.class)).thenReturn(updatedIngredientDTO);

        assertThat(updatedIngredientDTO).isEqualTo(this.service.update(ingredientDTO, this.id));


        verify(this.repo, times(1)).findById(1L);
        verify(this.repo, times(1)).save(updatedIngredient);
    }

    @Test
    void deleteTest() {

        when(this.repo.existsById(id)).thenReturn(true, false);

        assertThat(this.service.delete(id)).isTrue();

        verify(this.repo, times(1)).deleteById(id);

        verify(this.repo, times(2)).existsById(id);
    }

}