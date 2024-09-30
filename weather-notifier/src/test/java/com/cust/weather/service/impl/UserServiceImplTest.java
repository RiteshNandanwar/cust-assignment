package com.cust.weather.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cust.weather.entity.User;
import com.cust.weather.entity.Weather;
import com.cust.weather.excetion.UserNotFoundException;
import com.cust.weather.repo.UserRepository;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testActivateUser() {
        String userId = "123";
        userServiceImpl.activateUser(userId);
        verify(userRepository, times(1)).updateUserStatus(userId, true);
    }

    @Test
    public void testDeactivateUser() {
        String userId = "123";
        userServiceImpl.deactivateUser(userId);
        verify(userRepository, times(1)).updateUserStatus(userId, false);
    }

    @Test
    public void testGetUser_Success() {
        String username = "testUser";
        User user = new User();
        user.setId("123");
        when(userRepository.findByUsernameAndActiveTrue(username)).thenReturn(Optional.of(user));

        String userId = userServiceImpl.getUser(username);
        assertEquals("123", userId);
    }

    @Test
    public void testGetUser_UserNotFoundException() {
        String username = "testUser";
        when(userRepository.findByUsernameAndActiveTrue(username)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUser(username));
    }

    @Test
    public void testGetHistoryByUser_Success() {
        String username = "testUser";
        List<Weather> weatherList = List.of(new Weather());
        when(userRepository.findByUser(username)).thenReturn(Optional.of(weatherList));

        List<Weather> result = userServiceImpl.getHistoryByUser(username);
        assertEquals(weatherList, result);
    }

    @Test
    public void testGetHistoryByUser_UserNotFoundException() {
        String username = "testUser";
        when(userRepository.findByUser(username)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userServiceImpl.getHistoryByUser(username));
    }
}