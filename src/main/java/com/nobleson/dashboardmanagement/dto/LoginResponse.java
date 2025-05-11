package com.nobleson.dashboardmanagement.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class LoginResponse {
    private String username;
    private Set<String> roles;
    private Set<MenuDTO> menus;

    public LoginResponse(String username, Set<String> roles, Set<MenuDTO> menus) {
        this.username = username;
        this.roles = roles;
        this.menus = menus;
    }
}

