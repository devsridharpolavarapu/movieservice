package com.newsconcierge.movie.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NewsConciergeResponse<T> {

	@JsonProperty("success")
	private boolean success;

	@JsonProperty("message")
	private String message;

	@JsonProperty("data")
	private T data;

}
