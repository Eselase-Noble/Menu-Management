package com.nobleson.dashboardmanagement.service;

import com.nobleson.dashboardmanagement.DELETE_YN;
import com.nobleson.dashboardmanagement.dto.MenuDTO;
import com.nobleson.dashboardmanagement.dto.RoleDTO;
import com.nobleson.dashboardmanagement.mapper.RoleMapper;
import com.nobleson.dashboardmanagement.model.Role;
import com.nobleson.dashboardmanagement.repository.RoleRepository;
import com.nobleson.dashboardmanagement.serviceInterface.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    /**
     * Retrieves a role by its name.
     *
     * @param roleName the name of the role to retrieve
     * @return the Role object with the specified name, or null if not found
     */
    @Override
    public RoleDTO getRole(String roleName) {
        return roleMapper.roleToRoleDTO(
                roleRepository.findByRoleNameAndDELETE_YN(roleName, "N")
        );
    }

    /**
     * Adds a new role to the system.
     *
     * @param role the Role object to add
     * @return the added Role object
     */
    @Override
    public RoleDTO addRole(RoleDTO role) {
        role.setDELETE_YN(DELETE_YN.N);
        role.setCreatedOn(Timestamp.from(Instant.now()));
        role.setUpdatedOn(Timestamp.from(Instant.now()));
        return roleMapper.roleToRoleDTO(
                roleRepository.save(
                        roleMapper.roleDTOToRole(role)
                )
        );
    }

    /**
     * Updates an existing role in the system.
     *
     * @param role the Role object with updated information
     * @return the updated Role object
     */
    @Override
    public RoleDTO updateRole(RoleDTO role) {
        RoleDTO updatedRole = getRole(role.getRoleName());

        updatedRole.setRoleName((role.getRoleName() == null || !role.getRoleName().isEmpty()) ? updatedRole.getRoleName() : role.getRoleName());
        updatedRole.setDELETE_YN(DELETE_YN.N);
        updatedRole.setUpdatedOn(Timestamp.from(Instant.now()));
        return null;
    }

    /**
     * Deletes a role by its name.
     *
     * @param roleName the name of the role to delete
     * @return the deleted Role object, or null if the role was not found
     */
    @Override
    public void deleteRole(String roleName) {
      roleRepository.deleteRole(roleName);
    }

    /**
     * Retrieves all roles in the system.
     *
     * @return a list of all Role objects
     */
    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.getAllRoles()
                .stream().map(
                        roleMapper::roleToRoleDTO
                ).toList()
                ;
    }

    /**
     * Adds a single menu to a role in the system.
     *
     * @param roleName the role based on which the menu should be added
     * @param menu     the MenuDTO object representing the menu to be added to the role
     * @return the updated RoleDTO object after the menu has been added
     */
    @Override
    public RoleDTO addMenuToRole(String roleName, MenuDTO menu) {
        RoleDTO role = getRole(roleName);
        role.setMenus(
                Set.of(
                        menu
                )
        );
        return updateRole(role);
    }

    /**
     * Adds multiple menus to a role in the system.
     *
     * @param roleName
     * @param menus    a list of MenuDTO objects representing the menus to be added to the role
     * @return the updated RoleDTO object after the menus have been added
     */
    @Override
    public RoleDTO addMenusToRole(String roleName, Set<MenuDTO> menus) {
        RoleDTO role = getRole(roleName);
        role.setMenus(menus);
        return updateRole(role);
    }

    /**
     * Removes a single menu from a role in the system.
     *
     * @param roleName
     * @param menu     the MenuDTO object representing the menu to be removed from the role
     * @return the updated RoleDTO object after the menu has been removed
     */
    @Override
    public RoleDTO removeMenuFromRole(String roleName, MenuDTO menu) {

        return null;
    }

    /**
     * Removes multiple menus from a role in the system.
     *
     * @param roleName
     * @param menus    a list of MenuDTO objects representing the menus to be removed from the role
     * @return the updated RoleDTO object after the menus have been removed
     */
    @Override
    public RoleDTO removeMenusFromRole(String roleName, Set<MenuDTO> menus) {
        return null;
    }
}
