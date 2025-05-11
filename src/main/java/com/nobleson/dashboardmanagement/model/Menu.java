package com.nobleson.dashboardmanagement.model;

import com.nobleson.dashboardmanagement.DELETE_YN;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String menuId;
    private String parentId;
    @Column(unique = true)
    private String menuUrl;
    @Column(unique = true)
    private String menuName;
    private int level;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Menu parent;

    @OneToMany(mappedBy = "parent")
    private List<Menu> children = new ArrayList<>();

    @ManyToMany(mappedBy = "menus")
    private Set<Role> roles = new HashSet<>();

    @CreationTimestamp
    private Timestamp createdOn;
    @UpdateTimestamp
    private Timestamp updatedOn;

    private DELETE_YN DELETE_YN;
}

