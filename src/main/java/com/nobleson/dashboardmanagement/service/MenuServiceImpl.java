package com.nobleson.dashboardmanagement.service;

import com.nobleson.dashboardmanagement.DELETE_YN;
import com.nobleson.dashboardmanagement.dto.MenuDTO;
import com.nobleson.dashboardmanagement.dto.RoleDTO;
import com.nobleson.dashboardmanagement.mapper.MenuMapper;
import com.nobleson.dashboardmanagement.model.Menu;
import com.nobleson.dashboardmanagement.model.Role;
import com.nobleson.dashboardmanagement.repository.MenuRepository;
import com.nobleson.dashboardmanagement.repository.RoleRepository;
import com.nobleson.dashboardmanagement.serviceInterface.MenuService;
import com.nobleson.dashboardmanagement.tree.MenuConverter;
import com.nobleson.dashboardmanagement.tree.MenuTree;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    private final RoleRepository roleRepository;

    /**
     * Adds a new menu to the system.
     *
     * @param menuDTO the MenuDTO object containing menu details to add
     * @return the added MenuDTO object
     */
    @Override
    public MenuDTO addMenu(MenuDTO menuDTO) {
        // Set default values
        Timestamp now = Timestamp.from(Instant.now());
        menuDTO.setDELETE_YN(DELETE_YN.N);
        menuDTO.setCreatedOn(now);
        menuDTO.setUpdatedOn(now);

        // Convert DTO to entity
        Menu menuEntity = menuMapper.MenuDTOToMenu(menuDTO);

        // Handle parent relationship if needed
        if (menuDTO.getParentId() != null) {
            Menu parent = menuRepository.findById(menuDTO.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent menu not found: " + menuDTO.getParentId()));
            menuEntity.setParent(parent);
        }

        // Handle roles if needed (optional)
        if (menuDTO.getRoleIds() != null && !menuDTO.getRoleIds().isEmpty()) {
            Set<Role> roles = new HashSet<>(roleRepository.findAllById(menuDTO.getRoleIds()));
            menuEntity.setRoles(roles);
        }

        // Save and return
        Menu savedMenu = menuRepository.save(menuEntity);
        return menuMapper.MenuToMenuDTO(savedMenu);
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
        updatedMenu.setParentId(
                (menuDTO.getParentId() == null
                ? updatedMenu.getParentId()
                        : menuDTO.getParentId())
        );

        updatedMenu.setChildrenId(
                menuDTO.getChildrenId() == null ? updatedMenu.getChildrenId() : menuDTO.getChildrenId()
        );

        updatedMenu.setSortOrder(
                menuDTO.getSortOrder() == null ? updatedMenu.getSortOrder() : menuDTO.getSortOrder()
        );


        // Update the roles associated with this menu
        updatedMenu.setRoleIds(
                (menuDTO.getRoleIds() == null || menuDTO.getRoleIds().isEmpty())
                        ? updatedMenu.getRoleIds()
                        : menuDTO.getRoleIds()
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
                        menuRepository.findMenuByMenuId(
                                menuName
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

    public List<MenuDTO> getMenuTreeForUser(String username) {
        List<Menu> allMenus = menuRepository.findAllMenusByUsername(username);
        MenuTree<Menu> menuTree = new MenuTree<>(
                allMenus,
                Comparator.comparingInt(Menu::getSortOrder)
        );
        return MenuConverter.convertToDTO(menuTree.getRoots());
    }

}
