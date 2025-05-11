package com.nobleson.dashboardmanagement.mapper;

import com.nobleson.dashboardmanagement.dto.RoleDTO;
import com.nobleson.dashboardmanagement.model.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

    Role roleDTOToRole(RoleDTO roleDTO);
    RoleDTO roleToRoleDTO(Role role);
}
