package com.nobleson.dashboardmanagement.service;

import com.nobleson.dashboardmanagement.dto.LoginRequest;
import com.nobleson.dashboardmanagement.dto.LoginResponse;
import com.nobleson.dashboardmanagement.dto.MenuDTO;
import com.nobleson.dashboardmanagement.model.Menu;
import com.nobleson.dashboardmanagement.model.Role;
import com.nobleson.dashboardmanagement.model.User;
import com.nobleson.dashboardmanagement.repository.UserRepository;
import com.nobleson.dashboardmanagement.serviceInterface.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Set<String> roleIds = user.getRoles().stream()
                .map(Role::getRoleId)
                .collect(Collectors.toSet());

        Set<Menu> menus = new HashSet<>();
        for (Role role : user.getRoles()) {
            menus.addAll(role.getMenus());
        }

        Set<MenuDTO> menuDTOs = menus.stream()
                .map(MenuDTO::new)
                .collect(Collectors.toSet());

        return new LoginResponse(user.getUsername(), roleIds, menuDTOs);
    }
}
