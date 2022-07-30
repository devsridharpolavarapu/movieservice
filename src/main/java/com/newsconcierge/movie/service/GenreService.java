package com.newsconcierge.movie.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.newsconcierge.movie.common.genre.GenreResponse;

@Service
public class GenreService {

	@Value("${api.themoviedb.url}")
	private String baseUrl;

	@Value("${api.themoviedb.key}")
	private String apiKey;

	@Value("${api.themoviedb.locale:en-AU}")
	private String language;

	/**
	 * Genre Wrapper.
	 * 
	 * @return
	 */
	public GenreResponse getGenres() {

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(baseUrl + "genre/movie/list")
				.queryParam("api_key", apiKey)
				.queryParam("language", language);

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

		ResponseEntity<GenreResponse> response = new RestTemplate().exchange(
				builder.toUriString(),
				HttpMethod.GET,
				new HttpEntity<String>(headers),
				GenreResponse.class
				);

		return response.getBody();
	}
}
