package com.bell.codingchallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Genres {
	
	@JsonProperty("Id")
	private Integer id;
	
	@JsonProperty("Name")
	private String name;

}
