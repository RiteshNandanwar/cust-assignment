package com.cust.weather.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cust.weather.entity.User;
import com.cust.weather.entity.Weather;
import com.cust.weather.excetion.UserNotFoundException;
import com.cust.weather.repo.UserRepository;
import com.cust.weather.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Activate a user by setting their status to true.
     *
     * @param userId the ID of the user to activate
     */
    public void activateUser(String userId) {
        userRepository.updateUserStatus(userId, true);
    }

    /**
     * Deactivate a user by setting their status to false.
     *
     * @param userId the ID of the user to deactivate
     */
    public void deactivateUser(String userId) {
        userRepository.updateUserStatus(userId, false);
    }

    /**
     * Get the ID of an active user by their username.
     *
     * @param username the username of the user
     * @return the ID of the user
     * @throws UserNotFoundException if the user is not found or not active
     */
    public String getUser(String username) {
        return userRepository.findByUsernameAndActiveTrue(username)
                .map(User::getId)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found"));
    }

    /**
     * Get the weather history for a user by their username.
     *
     * @param user the username of the user
     * @return a list of Weather entities
     * @throws UserNotFoundException if the user is not found
     */
    @Override
    public List<Weather> getHistoryByUser(String user) {
        return userRepository.findByUser(user)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + user + " not found"));
    }
}