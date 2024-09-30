package com.cust.weather.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.cust.weather.service.UserService;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testActivateUser() {
        String userId = "123";
        doNothing().when(userService).activateUser(userId);

        ResponseEntity<String> response = userController.activateUser(userId);
        assertEquals("User activated successfully.", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(userService, times(1)).activateUser(userId);
    }

    @Test
    public void testDeactivateUser() {
        String userId = "123";
        doNothing().when(userService).deactivateUser(userId);

        ResponseEntity<String> response = userController.deactivateUser(userId);
        assertEquals("User deactivated successfully.", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(userService, times(1)).deactivateUser(userId);
    }
}