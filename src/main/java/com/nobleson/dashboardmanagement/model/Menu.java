package com.nobleson.dashboardmanagement.model;

import com.nobleson.dashboardmanagement.DELETE_YN;
import com.nobleson.dashboardmanagement.tree.interfaces.TreeNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements TreeNode<Menu> {
    @Id
    private String menuId;

    private String menuUrl;

    private String menuName;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Menu parent;

    private Integer level;

    private Integer sortOrder;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sortOrder ASC")
    private List<Menu> children = new ArrayList<>();

    @ManyToMany(mappedBy = "menus")
    private Set<Role> roles = new HashSet<>();

    @CreationTimestamp
    private Timestamp createdOn;
    @UpdateTimestamp
    private Timestamp updatedOn;
    @Enumerated(EnumType.STRING)
    private DELETE_YN DELETE_YN;

    public Menu(String id, String menuName, String menuUrl, Integer level, Integer sortOrder) {
        this.menuId = id;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.level = level;
        this.sortOrder = sortOrder;
    }

    /**
     * @return
     */
    @Override
    public String getMenuId() {
        return menuId;
    }

    /**
     * @return
     */
    @Override
    public String getParentId() {
        return parent != null ? parent.getMenuId() : null;
    }

    /**
     * @return
     */
    @Override
    public Menu getData() {
        return this;
    }

    /**
     * @param child
     */
    @Override
    public void addChild(Menu child) {
        children.add(child);
        child.setParent(this);
    }
}

