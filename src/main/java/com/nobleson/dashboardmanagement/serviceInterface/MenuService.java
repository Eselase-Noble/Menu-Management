package com.nobleson.dashboardmanagement.serviceInterface;

import com.nobleson.dashboardmanagement.dto.MenuDTO;

import java.util.List;

public interface MenuService {

    /**
     * Adds a new menu to the system.
     *
     * @param menuDTO the MenuDTO object containing menu details to add
     * @return the added MenuDTO object
     */
    MenuDTO addMenu(MenuDTO menuDTO);

    /**
     * Updates an existing menu in the system.
     *
     * @param menuDTO the MenuDTO object containing updated menu information
     * @return the updated MenuDTO object
     */
    MenuDTO updateMenu(MenuDTO menuDTO);

    /**
     * Deletes a menu by its ID.
     *
     * @param menuId the ID of the menu to delete
     */
    void deleteMenu(String menuId);

    /**
     * Retrieves a menu by its ID.
     *
     * @param menuId the ID of the menu to retrieve
     * @return the MenuDTO object with the specified ID, or null if not found
     */
    MenuDTO getMenuById(String  menuId);

    /**
     * Retrieves all menus in the system.
     *
     * @return a list of all MenuDTO objects
     */
    List<MenuDTO> getAllMenu();

    List<MenuDTO> getMenuTreeForUser(String username);

}
