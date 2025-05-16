package com.nobleson.dashboardmanagement.mapper;

import com.nobleson.dashboardmanagement.dto.RoleDTO;
import com.nobleson.dashboardmanagement.dto.UserDTO;
import com.nobleson.dashboardmanagement.model.Role;
import com.nobleson.dashboardmanagement.model.User;
import com.nobleson.dashboardmanagement.resolver.RoleResolver;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {RoleResolver.class})
public interface UserMapper {
    @Mapping(target = "roleIds", source = "roles", qualifiedByName = "toRoleIds")
    UserDTO UserToUserDTO(User user);

    @Mapping(target = "roles", source = "roleIds", qualifiedByName = "toRoles")
    User UserDTOToUser(UserDTO roleDTO);
}

