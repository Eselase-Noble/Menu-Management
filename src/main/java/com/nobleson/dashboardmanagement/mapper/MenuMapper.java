package com.nobleson.dashboardmanagement.mapper;

import com.nobleson.dashboardmanagement.dto.MenuDTO;
import com.nobleson.dashboardmanagement.model.Menu;
import com.nobleson.dashboardmanagement.model.Role;
import org.mapstruct.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(target = "parentId", source = "parent.menuId")
    @Mapping(target = "childrenId", expression = "java(mapChildrenIds(menu.getChildren()))")
    @Mapping(target = "roleIds", expression = "java(mapRoleIds(menu.getRoles()))")
    MenuDTO MenuToMenuDTO(Menu menu);

    @Mapping(target = "parent", ignore = true) // Need to set parent manually later
    @Mapping(target = "children", ignore = true) // Set manually if needed
    @Mapping(target = "roles", ignore = true) // Set manually from roleIds
    Menu MenuDTOToMenu(MenuDTO menuDTO);

    default List<String> mapChildrenIds(List<Menu> children) {
        if (children == null) return null;
        return children.stream()
                .map(Menu::getMenuId)
                .collect(Collectors.toList());
    }

    default Set<String> mapRoleIds(Set<Role> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(Role::getRoleId) // Assumes Role has getRoleId()
                .collect(Collectors.toSet());
    }
}

