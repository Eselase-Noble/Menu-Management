package com.nobleson.dashboardmanagement.dto;

import com.nobleson.dashboardmanagement.DELETE_YN;
import com.nobleson.dashboardmanagement.model.Menu;
import com.nobleson.dashboardmanagement.model.Role;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class MenuDTO {
    private String menuId;
    private String menuUrl;
    private String menuName;
    private int level;
    private String parentId;
    private Set<Role> roles = new HashSet<>();
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private DELETE_YN DELETE_YN;
    public MenuDTO(Menu menu) {
        this.menuId = menu.getMenuId();
        this.menuUrl = menu.getMenuUrl();
        this.menuName = menu.getMenuName();
        this.level = menu.getLevel();
    }
}

