package com.nobleson.dashboardmanagement.resolver;

import com.nobleson.dashboardmanagement.model.Menu;
import com.nobleson.dashboardmanagement.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;



@Component
@RequiredArgsConstructor
public class MenuResolver {

    private final MenuRepository menuRepository;

    @Named("toMenus")
    public Set<Menu> toMenus(Set<String> menuIds) {
        if (menuIds == null) return null;
        return new HashSet<>(menuRepository.findAllById(menuIds));
    }

    @Named("toMenuIds")
    public Set<String> toMenuIds(Set<Menu> menus) {
        if (menus == null) return null;
        return menus.stream()
                .map(Menu::getMenuId)
                .collect(Collectors.toSet());
    }


}