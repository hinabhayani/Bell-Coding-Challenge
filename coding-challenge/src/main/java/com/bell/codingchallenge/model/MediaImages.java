package com.bell.codingchallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MediaImages {
	
	@JsonProperty("Type")
	private String type;
	
	@JsonProperty("Url")
	private String url;
	
	@JsonProperty("Width")
	private Integer width;
	
	@JsonProperty("Height")
	private Integer height;

}
