package com.newsconcierge.movie.common.genre;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder(toBuilder = true)
public class Genre {

	private String id; 
	
	private String name;
	
}
