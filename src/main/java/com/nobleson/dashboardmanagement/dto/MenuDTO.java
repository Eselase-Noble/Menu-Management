package com.nobleson.dashboardmanagement.dto;

import com.nobleson.dashboardmanagement.DELETE_YN;
import com.nobleson.dashboardmanagement.model.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    private String menuId;
    private String menuUrl;
    private String menuName;
    private int level;
    private Set<String> roleIds;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private Integer sortOrder;
    private DELETE_YN DELETE_YN;
    private List<String> childrenId;
    private String parentId;

    public MenuDTO(Menu menu) {
        this.menuId = menu.getMenuId();
        this.menuUrl = menu.getMenuUrl();
        this.menuName = menu.getMenuName();
        this.level = menu.getLevel();
    }

    public MenuDTO(String id, String name, String url, Integer level, List<String> childrenId ) {
        this.menuId = id;
        this.menuName = name;
        this.menuUrl = url;
        this.level = level;
        this.childrenId = childrenId;
    }

    public MenuDTO(String id, String name, String url, Integer level, Integer sortOrder) {
        this.menuId = id;
        this.menuName = name;
        this.menuUrl = url;
        this.level = level;
        this.sortOrder = sortOrder;
    }
}

