package com.nobleson.dashboardmanagement.mapper;

import com.nobleson.dashboardmanagement.dto.RoleDTO;
import com.nobleson.dashboardmanagement.model.Role;
import com.nobleson.dashboardmanagement.resolver.MenuResolver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MenuResolver.class})
public interface RoleMapper {

    @Mapping(target = "menuIds", source = "menus", qualifiedByName = "toMenuIds")
    RoleDTO roleToRoleDTO(Role role);

    @Mapping(target = "menus", source = "menuIds", qualifiedByName = "toMenus")
    Role roleDTOToRole(RoleDTO roleDTO);
}
