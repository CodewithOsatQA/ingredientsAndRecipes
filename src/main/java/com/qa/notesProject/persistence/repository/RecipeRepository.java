package com.qa.notesProject.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.notesProject.persistence.domain.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
