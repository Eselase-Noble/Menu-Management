package com.nobleson.dashboardmanagement.repository;

import com.nobleson.dashboardmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r where r.DELETE_YN = 'N'")
    List<Role> getAllRoles();

    Role findByRoleNameAndDELETE_YN(String name, String delete_yn);

    @Query("UPDATE Role r set r.DELETE_YN = 'N' where r.roleName = :roleName")
    void deleteRole(String roleName);

}

