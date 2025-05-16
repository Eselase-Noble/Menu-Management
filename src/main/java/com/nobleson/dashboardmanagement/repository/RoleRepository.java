package com.nobleson.dashboardmanagement.repository;

import com.nobleson.dashboardmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    @Query("SELECT r FROM Role r where r.DELETE_YN = com.nobleson.dashboardmanagement.DELETE_YN.N ")
    List<Role> getAllRoles();

    @Query("select r FROM  Role r where r.roleId = :roleId and  r.DELETE_YN = :delete_yn")
    Role findByRoleNameAndDELETE_YN(@Param("roleId") String name, @Param("delete_yn") String delete_yn);

    @Query("UPDATE Role r set r.DELETE_YN = com.nobleson.dashboardmanagement.DELETE_YN.N where r.roleId = :roleId")
    void deleteRole(@Param("roleId") String roleId);

}

