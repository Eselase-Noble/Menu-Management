package com.nobleson.dashboardmanagement.dto;

import com.nobleson.dashboardmanagement.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class LoginUserDetails implements UserDetails {

    private final User user;

    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles()
                .stream()
                .map(
                        role -> new SimpleGrantedAuthority(role.getRoleId())
                ).collect(Collectors.toSet());
    }

    /**
     * @return
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * @return
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }
}
