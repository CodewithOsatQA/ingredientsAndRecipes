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

@Service
public class IngredientService{
	
	private IngredientRepository repository;
	private ModelMapper mapper;
	
	@Autowired
	public IngredientService(IngredientRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	private IngredientDTO mapToDTO (Ingredients ingredient) {
		return this.mapper.map(ingredient, IngredientDTO.class);
		
	}
	public IngredientDTO create (Ingredients ingredient) {
		Ingredients ingredientCreated = this.repository.save(ingredient);
		IngredientDTO mapped = this.mapToDTO(ingredientCreated);
		return mapped;
	}
	
	public List<IngredientDTO> read(){
		List<Ingredients> found = this.repository.findAll();
		List<IngredientDTO> streamed = found.stream().map(this::mapToDTO).collect(Collectors.toList());
		return streamed;
	}
	
	public IngredientDTO read(Long id) {
		Ingredients found = this.repository.findById(id).orElseThrow(IngredientsNotFoundException::new);
		return this.mapToDTO(found);
				
	}
	public IngredientDTO update(IngredientDTO ingredientDTO, Long id) {
		Ingredients toUpdate = this.repository.findById(id).orElseThrow(IngredientsNotFoundException::new);
		
	}
}

