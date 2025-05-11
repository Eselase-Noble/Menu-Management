package com.nobleson.dashboardmanagement.controller;

import com.nobleson.dashboardmanagement.dto.MenuDTO;
import com.nobleson.dashboardmanagement.serviceInterface.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dashboard/v1/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;


    @GetMapping("/all")
    public ResponseEntity<List<MenuDTO>> getAllMenus() {
        return new ResponseEntity<>(menuService.getAllMenu(), HttpStatus.OK);
    }

    @GetMapping("/get-menu")
    public ResponseEntity<MenuDTO> getMenuById(@RequestParam("menuId") String menuId) {

        return new ResponseEntity<>(menuService.getMenuById(menuId), HttpStatus.OK);
    }

    @PostMapping("/add-menu")
    public ResponseEntity<MenuDTO> addMenu(@RequestBody MenuDTO menuDTO) {
        return new ResponseEntity<>(menuService.addMenu(menuDTO), HttpStatus.OK);
    }

    @PutMapping("/update-menu")
    public ResponseEntity<MenuDTO> updateMenu(@RequestBody MenuDTO menuDTO) {
        return new ResponseEntity<>(menuService.updateMenu(menuDTO), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteMenuById(@RequestParam("menuId") String menuId) {
        menuService.deleteMenu(menuId);
        return new ResponseEntity<>("Menu deleted", HttpStatus.OK);
    }
}
