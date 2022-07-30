package com.newsconcierge.movie.common.user;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder(toBuilder = true)
public class UserResponse {
	private String emailaddress;
	private UUID uniqueKey;
}
