package com.bell.codingchallenge.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediaDetails {

	@JsonProperty("Id")
	private Integer id;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("Desc")
	private String desc;

	@JsonProperty("ShortDesc")
	private String shortDesc;

	@JsonProperty("Images")
	private List<MediaImages> images;

	@JsonProperty("Genres")
	private List<Genres> genres;
	
}
