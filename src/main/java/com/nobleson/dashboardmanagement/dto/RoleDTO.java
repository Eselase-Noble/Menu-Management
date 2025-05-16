package com.nobleson.dashboardmanagement.dto;

import com.nobleson.dashboardmanagement.DELETE_YN;
import com.nobleson.dashboardmanagement.model.Menu;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private String roleId;

    private String roleName;

    private Set<String> menuIds = new HashSet<>();

    private Timestamp createdOn;

    private Timestamp updatedOn;

    private DELETE_YN DELETE_YN;
}
