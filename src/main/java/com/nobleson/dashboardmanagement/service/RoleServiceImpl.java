package com.nobleson.dashboardmanagement.service;

import com.nobleson.dashboardmanagement.DELETE_YN;
import com.nobleson.dashboardmanagement.dto.MenuDTO;
import com.nobleson.dashboardmanagement.dto.RoleDTO;
import com.nobleson.dashboardmanagement.mapper.RoleMapper;
import com.nobleson.dashboardmanagement.model.Menu;
import com.nobleson.dashboardmanagement.model.Role;
import com.nobleson.dashboardmanagement.repository.MenuRepository;
import com.nobleson.dashboardmanagement.repository.RoleRepository;
import com.nobleson.dashboardmanagement.serviceInterface.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final MenuRepository menuRepository;


    /**
     * Retrieves a role by its name.
     *
     * @param roleId the name of the role to retrieve
     * @return the Role object with the specified name, or null if not found
     */
    @Override
    public RoleDTO getRole(String roleId) {
        return roleMapper.roleToRoleDTO(
                roleRepository.findByRoleNameAndDELETE_YN(roleId, "N")
        );
    }

    /**
     * Adds a new role to the system.
     *
     * @param roleDTO the Role object to add
     * @return the added Role object
     */
    @Override
    public RoleDTO addRole(RoleDTO roleDTO) {
        Role role = roleMapper.roleDTOToRole(roleDTO);

        // 🧠 Ensure menus are managed (fetched from DB)
        if (role.getMenus() != null && !role.getMenus().isEmpty()) {
            Set<Menu> managedMenus = new HashSet<>();
            for (Menu menu : role.getMenus()) {
                Menu managedMenu = menuRepository.findById(menu.getMenuId())
                        .orElseThrow(() -> new RuntimeException("Menu not found: " + menu.getMenuId()));
                // ✅ Add role on owning side
                managedMenu.getRoles().add(role);
                managedMenus.add(managedMenu);
            }
            role.setMenus(managedMenus);
        }

        role.setDELETE_YN(DELETE_YN.N);
        role.setCreatedOn(Timestamp.from(Instant.now()));
        role.setUpdatedOn(Timestamp.from(Instant.now()));

        return roleMapper.roleToRoleDTO(roleRepository.save(role));
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
        return roleMapper.roleToRoleDTO(
                roleRepository.save(
                        roleMapper.roleDTOToRole(updatedRole)
                )
        );
    }

    /**
     * Deletes a role by its name.
     *
     * @param roleId the name of the role to delete
     * @return the deleted Role object, or null if the role was not found
     */
    @Override
    public void deleteRole(String roleId) {
      roleRepository.deleteRole(roleId);
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
     * @param roleId the role based on which the menu should be added
     * @param menu     the MenuDTO object representing the menu to be added to the role
     * @return the updated RoleDTO object after the menu has been added
     */
    @Override
    public RoleDTO addMenuToRole(String roleId, MenuDTO menu) {
        RoleDTO role = getRole(roleId);
        role.setMenuIds(
                Set.of(
                        menu.getMenuId()
                )
        );
        return updateRole(role);
    }

    /**
     * Adds multiple menus to a role in the system.
     *
     * @param roleId
     * @param menus    a list of MenuDTO objects representing the menus to be added to the role
     * @return the updated RoleDTO object after the menus have been added
     */
    @Override
    public RoleDTO addMenusToRole(String roleId, Set<String> menus) {
        RoleDTO role = getRole(roleId);

        role.setMenuIds(menus);

        return updateRole(role);
    }

    /**
     * Removes a single menu from a role in the system.
     *
     * @param roleId
     * @param menu     the MenuDTO object representing the menu to be removed from the role
     * @return the updated RoleDTO object after the menu has been removed
     */
    @Override
    public RoleDTO removeMenuFromRole(String roleId, String menu) {
        RoleDTO role = getRole(roleId);

        if (role == null) {
            throw new IllegalArgumentException("Role not found: " + roleId);
        }

        Set<String> menus = role.getMenuIds();

        if (menus != null && menus.contains(menu)) {
            menus.remove(menu);
            role.setMenuIds(menus);
        }

        return updateRole(role);
    }


    /**
     * Removes multiple menus from a role in the system.
     *
     * @param roleId
     * @param menus    a list of MenuDTO objects representing the menus to be removed from the role
     * @return the updated RoleDTO object after the menus have been removed
     */
    @Override
    public RoleDTO removeMenusFromRole(String roleId, Set<String> menus) {
        RoleDTO role = getRole(roleId);

        if (role == null) {
            throw new IllegalArgumentException("Role not found: " + roleId);
        }

        Set<String> existingMenus = role.getMenuIds();

        if (existingMenus != null && !existingMenus.isEmpty()) {
            existingMenus.removeAll(menus);
            role.setMenuIds(existingMenus);
        }

        return updateRole(role);
    }

}
