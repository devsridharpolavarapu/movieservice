package com.newsconcierge.movie.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
	@Cacheable(value="nowplaying")
	public MoviesResponse getNowPlaying(Integer page) {

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(baseUrl + "movie/now_playing")
				.queryParam("api_key", apiKey)
				.queryParam("language", language)
				.queryParam("page", page);

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

		ResponseEntity<MoviesResponse> response = new RestTemplate().exchange(
				builder.toUriString(),
				HttpMethod.GET,
				new HttpEntity<String>(headers),
				MoviesResponse.class
				);
		
		log.trace("NowPlaying results from tmdb endpoint");
		
		return response.getBody();
	}
	
	/**
	 * latest Wrapper.
	 * 
	 * @return
	 */
	@Cacheable(value="latest")
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
