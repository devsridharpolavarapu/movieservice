package com.newsconcierge.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newsconcierge.movie.common.Messages;
import com.newsconcierge.movie.common.NewsConciergeResponse;
import com.newsconcierge.movie.common.user.User;
import com.newsconcierge.movie.common.user.UserResponse;
import com.newsconcierge.movie.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path="user/register")
	public ResponseEntity<NewsConciergeResponse<UserResponse>> register(@RequestBody User user){
		try {
			if(!userService.exists(user)) {
				var response = userService.register(user);
				var conciergeResponse = new NewsConciergeResponse<UserResponse>(true, Messages.SUCCESS, response);
				return ResponseEntity.ok().body(conciergeResponse);
			}
			else {
				var conciergeResponse = new NewsConciergeResponse<UserResponse>(false, Messages.USER_EXISTS, null);
				return ResponseEntity.ok().body(conciergeResponse);
			}

		}
		catch (Exception e) {
			log.error("Exception while registering a user", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping(path="user/login" )
	public ResponseEntity<NewsConciergeResponse<UserResponse>> login(@RequestBody User user){
		try {

			var loginResponse = userService.login(user.getEmail(), user.getPassword());
			if(loginResponse != null) {
				return ResponseEntity.ok()
						.body(new NewsConciergeResponse<UserResponse>(true, Messages.SUCCESS, loginResponse));
			}
			else {
				var conciergeResponse = new NewsConciergeResponse<UserResponse>(false, Messages.UNIDENTIFIED_USER, null);
				return ResponseEntity.ok().body(conciergeResponse);
			}

		}
		catch (Exception e) {
			log.error("Exception while attempting a login", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
