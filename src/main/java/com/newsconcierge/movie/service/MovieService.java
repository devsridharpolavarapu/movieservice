package com.newsconcierge.movie.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.newsconcierge.movie.common.Movie;
import com.newsconcierge.movie.common.MoviesResponse;

@Service
public class MovieService {

	@Value("${api.themoviedb.url}")
	private String baseUrl;

	@Value("${api.themoviedb.key}")
	private String apiKey;
	
	@Value("${api.themoviedb.locale:en-AU}")
	private String language;

	/**
	 * now_playing Wrapper.
	 * 
	 * @return
	 */
	public MoviesResponse getNowPlaying(Optional<Integer> page) {

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(baseUrl + "movie/now_playing")
				.queryParam("api_key", apiKey)
				.queryParam("language", language)
				.queryParamIfPresent("page", page);

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

		ResponseEntity<MoviesResponse> response = new RestTemplate().exchange(
				builder.toUriString(),
				HttpMethod.GET,
				new HttpEntity<String>(headers),
				MoviesResponse.class
				);

		return response.getBody();
	}
	
	/**
	 * latest Wrapper.
	 * 
	 * @return
	 */
	public Movie getLatest() {

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(baseUrl + "movie/latest")
				.queryParam("api_key", apiKey)
				.queryParam("language", language);

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

		ResponseEntity<Movie> response = new RestTemplate().exchange(
				builder.toUriString(),
				HttpMethod.GET,
				new HttpEntity<String>(headers),
				Movie.class
				);

		return response.getBody();
	}
}
