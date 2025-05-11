package com.nobleson.dashboardmanagement.service;

import com.nobleson.dashboardmanagement.DELETE_YN;
import com.nobleson.dashboardmanagement.dto.MenuDTO;
import com.nobleson.dashboardmanagement.mapper.MenuMapper;
import com.nobleson.dashboardmanagement.repository.MenuRepository;
import com.nobleson.dashboardmanagement.serviceInterface.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    /**
     * Adds a new menu to the system.
     *
     * @param menuDTO the MenuDTO object containing menu details to add
     * @return the added MenuDTO object
     */
    @Override
    public MenuDTO addMenu(MenuDTO menuDTO) {


        menuDTO.setDELETE_YN(DELETE_YN.N);
        menuDTO.setCreatedOn(Timestamp.from(Instant.now()));
        menuDTO.setUpdatedOn(Timestamp.from(Instant.now()));

        return menuMapper.MenuToMenuDTO(
                menuRepository.save(
                        menuMapper.MenuDTOToMenu(
                                menuDTO
                        )
                )
        );
    }

    /**
     * Updates an existing menu in the system.
     *
     * @param menuDTO the MenuDTO object containing updated menu information
     * @return the updated MenuDTO object
     */
    @Override
    public MenuDTO updateMenu(MenuDTO menuDTO) {
        // Retrieve the existing menu by its name (assuming the name is unique)
        MenuDTO updatedMenu = getMenuById(menuDTO.getMenuName());

        // Update the fields only if they are not null or empty; otherwise, keep the existing value
        updatedMenu.setMenuName(
                (menuDTO.getMenuName() == null || menuDTO.getMenuName().isEmpty())
                        ? updatedMenu.getMenuName()
                        : menuDTO.getMenuName()
        );

        updatedMenu.setMenuId(
                (menuDTO.getMenuId() == null || menuDTO.getMenuId().isEmpty())
                        ? updatedMenu.getMenuId()
                        : menuDTO.getMenuId()
        );

        updatedMenu.setMenuUrl(
                (menuDTO.getMenuUrl() == null || menuDTO.getMenuUrl().isEmpty())
                        ? updatedMenu.getMenuUrl()
                        : menuDTO.getMenuUrl()
        );

        // Set the level only if the new level is provided
        updatedMenu.setLevel(
                (menuDTO.getLevel() == 0)
                        ? updatedMenu.getLevel()
                        : menuDTO.getLevel()
        );

        // Set the parent menu only if the new parent is provided
        updatedMenu.setParent(
                (menuDTO.getParent() == null)
                        ? updatedMenu.getParent()
                        : menuDTO.getParent()
        );

        // Update the children menus only if the new children list is not empty
        updatedMenu.setChildren(
                (menuDTO.getChildren() == null || menuDTO.getChildren().isEmpty())
                        ? updatedMenu.getChildren()
                        : menuDTO.getChildren()
        );

        // Update the roles associated with this menu
        updatedMenu.setRoles(
                (menuDTO.getRoles() == null || menuDTO.getRoles().isEmpty())
                        ? updatedMenu.getRoles()
                        : menuDTO.getRoles()
        );

        // Set DELETE_YN to 'N' (not deleted)
        updatedMenu.setDELETE_YN(DELETE_YN.N);

        // Update the timestamps
        updatedMenu.setUpdatedOn(Timestamp.from(Instant.now()));

        // Return the updated menu after saving it to the repository
        return menuMapper.MenuToMenuDTO(
                menuRepository.save(
                        menuMapper.MenuDTOToMenu(updatedMenu)
                )
        );
    }


    /**
     * Deletes a menu by its ID.
     *
     * @param menuId the ID of the menu to delete
     */
    @Override
    public void deleteMenu(String  menuId) {
        menuRepository.deleteMenu(menuId);
    }

    /**
     * Retrieves a menu by its menu name.
     *
     * @param menuName the ID of the menu to retrieve
     * @return the MenuDTO object with the specified ID, or null if not found
     */
    @Override
    public MenuDTO getMenuById(String  menuName) {
        return
                menuMapper.MenuToMenuDTO(
                        menuRepository.findByMenuNameAndDELETE_YN(
                                menuName, "N"
                        )
                );
    }

    /**
     * Retrieves all menus in the system.
     *
     * @return a list of all MenuDTO objects
     */
    @Override
    public List<MenuDTO> getAllMenu() {
        return menuRepository.findAllByDELETE_YN("N").stream()
                .map(
                        menuMapper::MenuToMenuDTO
                ).toList();
    }
}
