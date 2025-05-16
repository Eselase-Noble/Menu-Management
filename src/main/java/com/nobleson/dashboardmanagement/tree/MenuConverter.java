package com.nobleson.dashboardmanagement.tree;

import com.nobleson.dashboardmanagement.dto.MenuDTO;
import com.nobleson.dashboardmanagement.model.Menu;
import com.nobleson.dashboardmanagement.model.Role;
import com.nobleson.dashboardmanagement.tree.interfaces.TreeNode;

import java.util.List;
import java.util.stream.Collectors;

public class MenuConverter {
    public static List<MenuDTO> convertToDTO(List<Menu> nodes) {
        return nodes.stream()
                .map(MenuConverter::convertNode)
                .collect(Collectors.toList());
    }

    private static MenuDTO convertNode(Menu node) {
        MenuDTO dto = new MenuDTO();
        dto.setMenuId(node.getMenuId());
        dto.setMenuName(node.getMenuName());
        dto.setMenuUrl(node.getMenuUrl());
        dto.setLevel(node.getLevel());
        dto.setSortOrder(node.getSortOrder());
        dto.setCreatedOn(node.getCreatedOn());
        dto.setUpdatedOn(node.getUpdatedOn());
        dto.setDELETE_YN(node.getDELETE_YN());
        dto.setParentId(node.getParentId());

        if (node.getRoles() != null) {
            dto.setRoleIds(node.getRoles().stream()
                    .map(Role::getRoleId) // Now uses Long
                    .collect(Collectors.toSet()));
        }

        if (node.getChildren() != null && !node.getChildren().isEmpty()) {
            dto.setChildrenId(node.getChildren().stream()
                    .map(Menu::getMenuId)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}
