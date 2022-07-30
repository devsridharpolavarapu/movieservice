package com.newsconcierge.movie.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.newsconcierge.movie.common.user.User;
import com.newsconcierge.movie.common.user.UserResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	private static Map<String, User> users= new HashMap<>();
	
	@Value("${newsconcierge.cookie.domain}")
	private String cookieDomain;
	
	@Value("${newsconcierge.cookie.ttl:3600}")
	private Integer cookieTimeToLive;
	
	public boolean exists(final User user) {
		return users.containsKey(user.getEmail());
	}
	
	public UserResponse register(final User user) {
		
		user.setUniqueKey(UUID.randomUUID());
		users.put(user.getEmail().toLowerCase(), user);
		return UserResponse.builder().emailaddress(user.getEmail()).uniqueKey(user.getUniqueKey()).build();
	}
	
	public UserResponse login(final String username, final String password) {
		if(users.containsKey(username.toLowerCase())) {
			var user = users.get(username.toLowerCase());
			if(user.getPassword().equals(password)) {
				log.debug("user with {} found", username);
				return UserResponse.builder().emailaddress(user.getEmail()).uniqueKey(user.getUniqueKey()).build();
			}else {
				log.debug("user with {} NOT found", username);
			}
			
		}
		return null;
	}

}
