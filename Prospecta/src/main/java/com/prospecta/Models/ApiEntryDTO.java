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
public class ApiEntryDTO {
	
	@JsonProperty("API")
	private String api;
	
	@JsonProperty("Description")
	private String description;

}
