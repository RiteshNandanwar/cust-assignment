package com.cust.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cust.weather.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "User Controller", description = "Operations pertaining to user management")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Activate a user", description = "Activate a user by their ID.")
    @PostMapping("/user/activate")
    public ResponseEntity<String> activateUser(
            @Parameter(description = "ID of the user to be activated", required = true) @RequestParam String userId) {
        userService.activateUser(userId);
        return ResponseEntity.ok("User activated successfully.");
    }

    @Operation(summary = "Deactivate a user", description = "Deactivate a user by their ID.")
    @PostMapping("/user/deactivate")
    public ResponseEntity<String> deactivateUser(
            @Parameter(description = "ID of the user to be deactivated", required = true) @RequestParam String userId) {
        userService.deactivateUser(userId);
        return ResponseEntity.ok("User deactivated successfully.");
    }
}