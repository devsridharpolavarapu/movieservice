package com.newsconcierge.movie.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newsconcierge.movie.common.NewsConciergeResponse;
import com.newsconcierge.movie.common.Movie;
import com.newsconcierge.movie.common.MoviesResponse;
import com.newsconcierge.movie.common.genre.Genre;
import com.newsconcierge.movie.service.GenreService;
import com.newsconcierge.movie.service.MovieService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MovieController {

	@Autowired
	private GenreService genreService;
	
	@Autowired
	private MovieService movieService;
	
	@Value("${newsconcierge.cookie.ttl:180}")
	private Integer cacheMaxAge;

	@GetMapping(path = "/genres")
	public ResponseEntity<NewsConciergeResponse<List<Genre>>> getGenres(){
		try {
			var genres = genreService.getGenres();
			log.trace("{}", genres);
			var conciergeResponse = new NewsConciergeResponse<List<Genre>>(true, "success", genres.getGenres());
			return ResponseEntity.ok().cacheControl(CacheControl.maxAge(cacheMaxAge, TimeUnit.SECONDS)).body(conciergeResponse);
		}
		catch (Exception e) {
			log.error("Exception while returning Genres", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}
	
	@GetMapping(path="movies/nowplaying")
	public ResponseEntity<NewsConciergeResponse<MoviesResponse>> getNowPlaying(@RequestParam Optional<Integer> page){
		try {
			var movies = movieService.getNowPlaying(page);
			log.trace("{}", movies);
			var conciergeResponse = new NewsConciergeResponse<MoviesResponse>(true, "success", movies);
			return ResponseEntity.ok().cacheControl(CacheControl.maxAge(cacheMaxAge, TimeUnit.SECONDS)).body(conciergeResponse);
		}
		catch (Exception e) {
			log.error("Exception while returning Now Playing", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@GetMapping(path="movies/latest")
	public ResponseEntity<NewsConciergeResponse<Movie>> getLatest(){
		try {
			var movie = movieService.getLatest();
			log.trace("{}", movie);
			var conciergeResponse = new NewsConciergeResponse<Movie>(true, "success", movie);
			return ResponseEntity.ok().cacheControl(CacheControl.maxAge(cacheMaxAge, TimeUnit.SECONDS)).body(conciergeResponse);
		}
		catch (Exception e) {
			log.error("Exception while returning Now Playing", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
