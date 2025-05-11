package com.nobleson.dashboardmanagement.service;

import com.nobleson.dashboardmanagement.DELETE_YN;
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
}
