package com.nobleson.dashboardmanagement.repository;

import com.nobleson.dashboardmanagement.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, String> {

    Menu findByMenuNameAndDELETE_YN(String name, String delete_yn);

    Menu findByMenuUrl(String url, String delete_yn);

    List<Menu> findAllByDELETE_YN(String delete_yn);

    @Query("UPDATE Menu m set m.DELETE_YN = 'N' WHERE m.menuId = :menuId")
    void deleteMenu(String menuId);
}
