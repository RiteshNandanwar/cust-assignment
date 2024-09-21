package com.cust.weather.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cust.weather.entity.User;
import com.cust.weather.entity.Weather;

/**
 * Repository interface for User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Find an active user by username.
     *
     * @param username the username of the user
     * @return an Optional containing the active user if found, otherwise empty
     */
    Optional<User> findByUsernameAndActiveTrue(String username);

    /**
     * Update the status of a user.
     *
     * @param userId the ID of the user
     * @param status the new status to set
     */
    @Modifying
    @Query("UPDATE User u SET u.active = :status WHERE u.id = :userId")
    void updateUserStatus(@Param("userId") String userId, @Param("status") boolean status);

    /**
     * Find weather information by user.
     *
     * @param user the user for whom to find weather information
     * @return an Optional containing a list of Weather entities if found, otherwise empty
     */
    Optional<List<Weather>> findByUser(String user);
}