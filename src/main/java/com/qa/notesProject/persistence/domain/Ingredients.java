package com.qa.notesProject.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Ingredients {
	
	@Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String foodGroup;
	
	@Min((long) 0.1)
	private Double price;
	
	@Min((long)0.1)
	private Double weight;
	
	public Ingredients(String name, String foodGroup, Double price, Double weight) {
        super();
        this.name = name;
        this.foodGroup = foodGroup;
        this.price = price;
        this.weight = weight;
    }

}
