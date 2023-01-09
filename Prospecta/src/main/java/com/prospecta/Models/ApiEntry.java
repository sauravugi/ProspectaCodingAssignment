package com.prospecta.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiEntry {
	
	@JsonProperty("API")
	private String api;
	
	@JsonProperty("Description")
	private String description;
	
	@JsonProperty("Auth")
	private String auth;
	
	@JsonProperty("Cors")
	private String cors;
	
	@JsonProperty("HTTPS")
	private boolean https;
	
	@JsonProperty("Link")
	private String link;
	
	@JsonProperty("Category")
	private String category;

}
