package com.bell.codingchallenge.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medias {
	
	@JsonProperty("Items")
	private List<Items> items;

	@JsonProperty("ItemsType")
	private String itemsType;
}
