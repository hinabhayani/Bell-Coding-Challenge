package com.bell.codingchallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Genres {
	
	@JsonProperty("Id")
	private Integer id;
	
	@JsonProperty("Name")
	private String name;

}
