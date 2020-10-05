package com.qa.notesProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.notesProject.dto.IngredientDTO;
import com.qa.notesProject.exceptions.IngredientsNotFoundException;
import com.qa.notesProject.persistence.domain.Ingredients;
import com.qa.notesProject.persistence.repository.IngredientRepository;
import com.qa.notesProject.utils.BakedBeanUtils;



@Service
public class IngredientService {

    private IngredientRepository repo;

    private ModelMapper mapper;

    @Autowired
    public IngredientService(IngredientRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private IngredientDTO mapToDTO(Ingredients ingredient) {
        return this.mapper.map(ingredient, IngredientDTO.class);
    }

    public IngredientDTO create(Ingredients ingredient) {
        Ingredients created = this.repo.save(ingredient);
        IngredientDTO mapped = this.mapToDTO(created);
        return mapped;
    }

    public List<IngredientDTO> read() {
        List<Ingredients> found = this.repo.findAll();
        List<IngredientDTO> streamed = found.stream().map(this::mapToDTO).collect(Collectors.toList());
        return streamed;
    }

    public IngredientDTO read(Long id) {
        Ingredients found = this.repo.findById(id).orElseThrow(IngredientsNotFoundException::new);
        return this.mapToDTO(found);
    }

    public IngredientDTO update(IngredientDTO ingredientDTO, Long id) {
        Ingredients toUpdate = this.repo.findById(id).orElseThrow(IngredientsNotFoundException::new);
        BakedBeanUtils.mergeNotNUll(ingredientDTO, toUpdate);
        return this.mapToDTO(this.repo.save(toUpdate));
    }

    public boolean delete(Long id) {
        if (!this.repo.existsById(id)) {
            throw new IngredientsNotFoundException();
        }
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

}
