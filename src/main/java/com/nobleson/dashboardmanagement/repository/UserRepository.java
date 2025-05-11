package com.nobleson.dashboardmanagement.repository;

import com.nobleson.dashboardmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByDELETE_YN(String delete_yn);

    Optional<User> findByUsername(String username);

    User getUserByIdAndDELETE_YN(Long id, String delete_yn);

    @Query("UPDATE User u SET u.DELETE_YN = 'N' WHERE u.id = :id ")
    void deleteUser(@Param("id") Long id);
}

