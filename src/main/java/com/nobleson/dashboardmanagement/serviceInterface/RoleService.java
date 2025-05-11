package com.nobleson.dashboardmanagement.serviceInterface;

import com.nobleson.dashboardmanagement.dto.RoleDTO;
import com.nobleson.dashboardmanagement.model.Role;

import java.util.List;

public interface RoleService {

    /**
     * Retrieves a role by its name.
     *
     * @param roleName the name of the role to retrieve
     * @return the Role object with the specified name, or null if not found
     */
    RoleDTO getRole(String roleName);

    /**
     * Adds a new role to the system.
     *
     * @param role the Role object to add
     * @return the added Role object
     */
    RoleDTO addRole(RoleDTO role);

    /**
     * Updates an existing role in the system.
     *
     * @param role the Role object with updated information
     * @return the updated Role object
     */
    RoleDTO updateRole(RoleDTO role);

    /**
     * Deletes a role by its name.
     *
     * @param roleName the name of the role to delete
     * @return the deleted Role object, or null if the role was not found
     */
    void deleteRole(String roleName);

    /**
     * Retrieves all roles in the system.
     *
     * @return a list of all Role objects
     */
    List<RoleDTO> getAllRoles();

}
