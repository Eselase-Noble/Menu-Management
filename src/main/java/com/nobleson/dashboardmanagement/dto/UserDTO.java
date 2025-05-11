package com.nobleson.dashboardmanagement.dto;

import com.nobleson.dashboardmanagement.DELETE_YN;
import com.nobleson.dashboardmanagement.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    private Set<Role> roles;

    private Timestamp createdOn;
    private Timestamp updatedOn;

    private DELETE_YN DELETE_YN;
}
