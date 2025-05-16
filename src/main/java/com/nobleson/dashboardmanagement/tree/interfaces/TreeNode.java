package com.nobleson.dashboardmanagement.tree.interfaces;

import java.util.List;

public interface TreeNode<T> {
    String getMenuId();
    String getParentId();
    T getData();
    List<T> getChildren();
    void addChild(T child);
}
