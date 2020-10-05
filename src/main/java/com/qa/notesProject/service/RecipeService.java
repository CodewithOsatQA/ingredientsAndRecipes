package com.qa.notesProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.notesProject.dto.RecipeDTO;
import com.qa.notesProject.exceptions.RecipeNotFoundException;
import com.qa.notesProject.persistence.domain.Recipe;
import com.qa.notesProject.persistence.repository.RecipeRepository;
import com.qa.notesProject.utils.BakedBeanUtils;

@Service
public class RecipeService {
	private RecipeRepository repository;
	private ModelMapper mapper;
	
	@Autowired
	public RecipeService(RecipeRepository repo, ModelMapper mapper) {
		this.mapper = mapper;
		this.repository = repo;
	}
	private RecipeDTO mapToDTO(Recipe recipe) {
		return this.mapper.map(recipe, RecipeDTO.class);
	}
	
	public RecipeDTO create(Recipe recipe) {
		Recipe recipeCreated = this.repository.save(recipe);
		return this.mapToDTO(recipeCreated);
	}
	
	public List<RecipeDTO> read(){
		List<Recipe> found = this.repository.findAll();
		List<RecipeDTO> stream = found.stream().map(this::mapToDTO).collect(Collectors.toList());
		return stream;
	}
	
	public RecipeDTO read(Long id) {
		Recipe found = this.repository.findById(id).orElseThrow(RecipeNotFoundException::new);
		return this.mapToDTO(found);
	}
	
	public RecipeDTO update(RecipeDTO recipeDTO, Long id) {
		Recipe toChange = this.repository.findById(id).orElseThrow(RecipeNotFoundException::new);
		BakedBeanUtils.mergeNotNUll(recipeDTO, toChange);
		return this.mapToDTO(this.repository.save(toChange));
		
	}
	public boolean delete(Long id) {
		if (this.repository.existsById(id)) {
			throw new RecipeNotFoundException();
		}
		this.repository.deleteById(id);
		return !this.repository.existsById(id);
	
	}
}
