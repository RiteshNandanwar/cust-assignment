package com.cust.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cust.weather.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Endpoint to activate a user by userId.
	 *
	 * @param userId the ID of the user to activate
	 * @return ResponseEntity with a success message
	 */
	@PutMapping("/{userId}/activate")
	public ResponseEntity<String> activateUser(@PathVariable String userId) {
		userService.activateUser(userId);
		return ResponseEntity.ok("User activated successfully.");
	}

	/**
	 * Endpoint to deactivate a user by userId.
	 *
	 * @param userId the ID of the user to deactivate
	 * @return ResponseEntity with a success message
	 */
	@PutMapping("/{userId}/deactivate")
	public ResponseEntity<String> deactivateUser(@PathVariable String userId) {
		userService.deactivateUser(userId);
		return ResponseEntity.ok("User deactivated successfully.");
	}
}