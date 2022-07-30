package com.newsconcierge.movie.common;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoviesResponse {
	
	@JsonProperty("dates")
	private DateRange dates;
	
	@JsonProperty("page")
	private Integer page;
	
	@JsonProperty("results")
	@Builder.Default
	private List<Movie> results = new ArrayList<>();
	
	@JsonProperty("total_pages")
	private Integer totalPages;
	
	@JsonProperty("total_results")
	private Integer totalResults;
}
