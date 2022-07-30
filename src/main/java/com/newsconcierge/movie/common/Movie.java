package com.newsconcierge.movie.common;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder(toBuilder = true)
public class Movie {

	@JsonProperty("poster_path")
	public String posterPath;
	
	@JsonProperty("adult")
	public Boolean adult;
	
	@JsonProperty("overview")
	public String overview;
	
	@JsonProperty("release_date")
	public String releaseDate;
	
	@JsonProperty("genre_ids")
	@Builder.Default
	public List<Integer> genreIds = new ArrayList<>();
	
	@JsonProperty("id")
	public Integer id;
	
	@JsonProperty("original_title")
	public String originalTitle;
	
	@JsonProperty("original_language")
	public String originalLanguage;
	
	@JsonProperty("title")
	public String title;
	
	@JsonProperty("backdrop_path")
	public String backdropPath;
	
	@JsonProperty("popularity")
	public Double popularity;
	
	@JsonProperty("vote_count")
	public Integer voteCount;
	
	@JsonProperty("video")
	public Boolean video;
	
	@JsonProperty("vote_average")
	public Double voteAverage;
	
}
