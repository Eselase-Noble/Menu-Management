package com.nobleson.dashboardmanagement.serviceInterface;

import com.nobleson.dashboardmanagement.dto.MenuDTO;
import com.nobleson.dashboardmanagement.dto.RoleDTO;
import com.nobleson.dashboardmanagement.model.Role;

import java.util.List;
import java.util.Set;

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


    /**
     * Adds a single menu to a role in the system.
     *
     * @param roleName the role based on which the menu should be added
     * @param menu the MenuDTO object representing the menu to be added to the role
     * @return the updated RoleDTO object after the menu has been added
     */
    RoleDTO addMenuToRole(String roleName, MenuDTO menu);

    /**
     * Adds multiple menus to a role in the system.
     *
     * @param menus a list of MenuDTO objects representing the menus to be added to the role
     * @return the updated RoleDTO object after the menus have been added
     */
    RoleDTO addMenusToRole(String roleName, Set<String> menus);

    /**
     * Removes a single menu from a role in the system.
     *
     * @param menu the MenuDTO object representing the menu to be removed from the role
     * @return the updated RoleDTO object after the menu has been removed
     */
    RoleDTO removeMenuFromRole(String roleName,String menu);

    /**
     * Removes multiple menus from a role in the system.
     *
     * @param menus a list of MenuDTO objects representing the menus to be removed from the role
     * @return the updated RoleDTO object after the menus have been removed
     */
    RoleDTO removeMenusFromRole(String roleName,Set<String> menus);



}
