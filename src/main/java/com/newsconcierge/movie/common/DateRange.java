package com.newsconcierge.movie.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder(toBuilder = true)
public class DateRange implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("maximum")
	private String maximum;
	@JsonProperty("minimum")
	private String minimum;
}
