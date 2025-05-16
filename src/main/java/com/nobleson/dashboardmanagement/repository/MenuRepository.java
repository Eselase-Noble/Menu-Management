package com.nobleson.dashboardmanagement.repository;

import com.nobleson.dashboardmanagement.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, String> {


    @Query("select m From Menu m where m.DELETE_YN = com.nobleson.dashboardmanagement.DELETE_YN.N ")
    List<Menu> findAllByDELETE_YN(String delete_yn);

    @Modifying
    @Query("UPDATE Menu m set m.DELETE_YN = com.nobleson.dashboardmanagement.DELETE_YN.Y WHERE m.menuId = :menuId")
    void deleteMenu(String menuId);

    @Query("select m from Menu m where m.DELETE_YN = com.nobleson.dashboardmanagement.DELETE_YN.N and m.menuId = :menuId")
    Menu findMenuByMenuId(@Param("menuId") String menuId);


    @Query("SELECT DISTINCT m FROM Menu m " +
            "JOIN m.roles r " +
            "JOIN r.users u " +
            "WHERE u.username = :username " +
            "AND m.parent IS NULL " +
            "ORDER BY m.sortOrder")
    List<Menu> findRootMenusByUsername(@Param("username") String username);

    @Query("SELECT m FROM Menu m " +
            "JOIN m.roles r " +
            "JOIN r.users u " +
            "WHERE u.username = :username " +
            "and m.DELETE_YN = com.nobleson.dashboardmanagement.DELETE_YN.N " +
            "and u.DELETE_YN = com.nobleson.dashboardmanagement.DELETE_YN.N " +
            "and r.DELETE_YN = com.nobleson.dashboardmanagement.DELETE_YN.N  ")
    List<Menu> findAllMenusByUsername(@Param("username") String username);
}
