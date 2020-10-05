package com.qa.notesProject.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.notesProject.dto.RecipeDTO;
import com.qa.notesProject.persistence.domain.Recipe;
import com.qa.notesProject.service.RecipeService;


@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService service;

    @Autowired
    public RecipeController(RecipeService service) {
        super();
        this.service = service;
    }

//    @PostMapping("/create")
//    public ResponseEntity<BandDTO> create(@RequestBody BandDTO bandDTO) {
//        return new ResponseEntity<>(this.service.create(bandDTO), HttpStatus.CREATED);
//    }

    @PostMapping("/create")
    public ResponseEntity<RecipeDTO> create(@RequestBody Recipe recipe) {
        RecipeDTO created = this.service.create(recipe);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<RecipeDTO>> read() {
        return ResponseEntity.ok(this.service.read());
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<RecipeDTO> read(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.read(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RecipeDTO> update(@PathVariable Long id, @RequestBody RecipeDTO recipeDTO) {
        return new ResponseEntity<>(this.service.update(recipeDTO, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RecipeDTO> delete(@PathVariable Long id) {
        return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
