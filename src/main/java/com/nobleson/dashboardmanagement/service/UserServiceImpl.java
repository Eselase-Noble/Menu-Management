package com.nobleson.dashboardmanagement.service;

import com.nobleson.dashboardmanagement.DELETE_YN;
import com.nobleson.dashboardmanagement.dto.RoleDTO;
import com.nobleson.dashboardmanagement.dto.UserDTO;
import com.nobleson.dashboardmanagement.mapper.UserMapper;
import com.nobleson.dashboardmanagement.model.Role;
import com.nobleson.dashboardmanagement.model.User;
import com.nobleson.dashboardmanagement.repository.RoleRepository;
import com.nobleson.dashboardmanagement.repository.UserRepository;
import com.nobleson.dashboardmanagement.serviceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    /**
     * Adds a new user to the system.
     *
     * @param userDTO the data transfer object (DTO) containing the user's details.
     * @return the added UserDTO with the assigned ID and any other updates.
     */
    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = userMapper.UserDTOToUser(userDTO);
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            Set<Role> managedRoles = new HashSet<>();
            for (Role role : user.getRoles()){
                Role managedRole = roleRepository.findById(
                        role.getRoleId()
                ).orElseThrow(() -> new RuntimeException("Role not found"));
                // ✅ Add user on owning side
                managedRole.getUsers().add(user);
                managedRoles.add(managedRole);
            }
            user.setRoles(managedRoles);
        }


        userDTO.setDELETE_YN(DELETE_YN.N);
        userDTO.setCreatedOn(Timestamp.from(Instant.now()));
        userDTO.setUpdatedOn(Timestamp.from(Instant.now()));

        return userMapper.UserToUserDTO(
                userRepository.save(userMapper.UserDTOToUser(userDTO))
        );
    }

    /**
     * Updates the details of an existing user in the system.
     *
     * @param userDTO the data transfer object (DTO) containing the updated user's details.
     * @return the updated UserDTO.
     */
    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        UserDTO updatedUser = getUserById(userDTO.getId());
        updatedUser.setFirstName((userDTO.getFirstName() == null || userDTO.getFirstName().isEmpty()) ? updatedUser.getFirstName() : userDTO.getFirstName());
        updatedUser.setLastName((userDTO.getLastName() == null || userDTO.getLastName().isEmpty()) ? updatedUser.getLastName() : userDTO.getLastName());
        updatedUser.setUsername((userDTO.getUsername() == null || userDTO.getUsername().isEmpty()) ? updatedUser.getUsername() : userDTO.getUsername());
        updatedUser.setRoleIds((userDTO.getRoleIds() == null) ? updatedUser.getRoleIds() : userDTO.getRoleIds());
        updatedUser.setDELETE_YN(DELETE_YN.N);

        return userMapper.UserToUserDTO(
                userRepository.save(
                        userMapper.UserDTOToUser(updatedUser)
                )
        );
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the unique identifier of the user.
     * @return the UserDTO corresponding to the provided ID, or null if no user is found.
     */
    @Override
    public UserDTO getUserById(Long id) {
        return userMapper
                .UserToUserDTO(
                        userRepository.getUserByIdAndDELETE_YN(
                                id, "N"
                        )
                );
    }

    /**
     * Retrieves a list of all users in the system.
     *
     * @return a list of UserDTO objects representing all users.
     */
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAllByDELETE_YN("N")
                .stream().map(
                        userMapper::UserToUserDTO
                ).toList();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }

    /**
     * @param id
     * @param roleDTO
     * @return
     */
    @Override
    public UserDTO addRoleToUser(Long id, String roleDTO) {
        UserDTO userDTO = getUserById(id);
        userDTO.setRoleIds(
                Set.of(
                        roleDTO
                )
        );
        return updateUser(userDTO);
    }
}
