package com.nobleson.dashboardmanagement.serviceInterface;

import com.nobleson.dashboardmanagement.dto.RoleDTO;
import com.nobleson.dashboardmanagement.dto.UserDTO;

import java.util.List;

public interface UserService {

    /**
     * Adds a new user to the system.
     *
     * @param userDTO the data transfer object (DTO) containing the user's details.
     * @return the added UserDTO with the assigned ID and any other updates.
     */
    UserDTO addUser(UserDTO userDTO);

    /**
     * Updates the details of an existing user in the system.
     *
     * @param userDTO the data transfer object (DTO) containing the updated user's details.
     * @return the updated UserDTO.
     */
    UserDTO updateUser(UserDTO userDTO);

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the unique identifier of the user.
     * @return the UserDTO corresponding to the provided ID, or null if no user is found.
     */
    UserDTO getUserById(Long id);

    /**
     * Retrieves a list of all users in the system.
     *
     * @return a list of UserDTO objects representing all users.
     */
    List<UserDTO> getAllUsers();

    /**
     * Delete a user from the system
     * @param id the user id to be deleted
     */
    void deleteUser(Long id);

    UserDTO addRoleToUser(Long id, String roleDTO);

}
