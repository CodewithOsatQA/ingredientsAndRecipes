package com.qa.notesProject.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.notesProject.dto.IngredientDTO;
import com.qa.notesProject.persistence.domain.Ingredients;
import com.qa.notesProject.service.IngredientService;



@RestController
@CrossOrigin
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientService service;

    @Autowired
    public IngredientController(IngredientService service) {
        super();
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<IngredientDTO> create(@RequestBody Ingredients ingredient) {
        IngredientDTO created = this.service.create(ingredient);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<IngredientDTO> read(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.read(id));
    }

    @GetMapping("/read")
    public ResponseEntity<List<IngredientDTO>> read() {
        return ResponseEntity.ok(this.service.read());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<IngredientDTO> update(@PathVariable Long id, @RequestBody IngredientDTO ingredientDTO) {
        return new ResponseEntity<>(this.service.update(ingredientDTO, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<IngredientDTO> delete(@PathVariable Long id) {
        return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // 204
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }

}
