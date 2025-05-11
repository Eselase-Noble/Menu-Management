package com.nobleson.dashboardmanagement.controller;

import com.nobleson.dashboardmanagement.dto.RoleDTO;
import com.nobleson.dashboardmanagement.dto.UserDTO;
import com.nobleson.dashboardmanagement.serviceInterface.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dashboard/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;


    @GetMapping("/all")
    public ResponseEntity<List<RoleDTO>> getRoles() {

        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/get-role")
    public ResponseEntity<RoleDTO> getRoleById(@RequestParam("roleName") String  roleName) {
        return new ResponseEntity<>(roleService.getRole(roleName), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<RoleDTO> addRole(@RequestBody RoleDTO role) {
        return new ResponseEntity<>(roleService.addRole(role), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<RoleDTO> updateRole(@RequestBody RoleDTO role) {
        return new ResponseEntity<>(roleService.updateRole(role), HttpStatus.OK);
    }

    @PutMapping("/delete")
    public ResponseEntity<String> deleteRole(@RequestParam("roleName") String  roleName) {
        roleService.deleteRole(roleName);
        return new ResponseEntity<>("User successfully deleted",HttpStatus.OK);
    }
}
