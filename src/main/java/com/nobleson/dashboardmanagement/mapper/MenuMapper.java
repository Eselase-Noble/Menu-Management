package com.nobleson.dashboardmanagement.mapper;

import com.nobleson.dashboardmanagement.dto.MenuDTO;
import com.nobleson.dashboardmanagement.model.Menu;
import org.mapstruct.Mapper;

@Mapper
public interface MenuMapper {

    Menu MenuDTOToMenu(MenuDTO menuDTO);
    MenuDTO MenuToMenuDTO(Menu menu);
}
