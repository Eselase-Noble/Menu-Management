package com.nobleson.dashboardmanagement.resolver;

import com.nobleson.dashboardmanagement.model.Menu;
import com.nobleson.dashboardmanagement.model.Role;
import com.nobleson.dashboardmanagement.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleResolver {
    private final RoleRepository roleRepository;

    @Named("toRoles")
    public Set<Role> toRoles(Set<String> roleIds) {
        if (roleIds == null) return null;
        return new HashSet<>(roleRepository.findAllById(roleIds));
    }

    @Named("toRoleIds")
    public Set<String> toRoleIds(Set<Role> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(Role::getRoleId)
                .collect(Collectors.toSet());
    }

}
