package com.newsconcierge.movie.common.genre;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.ALWAYS)
public class GenreResponse {
	
	private List<Genre> genres;
	
}
