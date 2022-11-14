package com.bell.codingchallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items {
	
	@JsonProperty("Id")
	private Integer id;

	@JsonProperty("Name")
	private String name;

}
