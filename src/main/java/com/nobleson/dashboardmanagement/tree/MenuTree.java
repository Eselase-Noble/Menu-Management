package com.nobleson.dashboardmanagement.tree;

import com.nobleson.dashboardmanagement.tree.interfaces.TreeNode;

import java.util.*;

public class MenuTree<T extends TreeNode<T>> {
    private final Map<String, T> nodeMap;
    private final List<T> roots;
    private final Comparator<T> comparator;

    public MenuTree(List<T> nodes) {
        this(nodes, null);
    }

    public MenuTree(List<T> nodes, Comparator<T> comparator) {
        this.nodeMap = new HashMap<>();
        this.roots = new ArrayList<>();
        this.comparator = comparator;
        buildTree(nodes);
    }

    private void buildTree(List<T> nodes) {
        // First pass: create all nodes
        for (T node : nodes) {
            nodeMap.put(node.getMenuId(), node);
        }

        // Second pass: build parent-child relationships
        for (T node : nodes) {
            String parentId = node.getParentId();
            if (parentId == null || parentId.isEmpty()) {
                roots.add(node);
            } else {
                T parent = nodeMap.get(parentId);
                if (parent != null) {
                    parent.addChild(node);
                }
            }
        }

        // Sort if comparator is provided
        if (comparator != null) {
            sortTree(roots);
        }
    }

    private void sortTree(List<T> nodes) {
        nodes.sort(comparator);
        for (T node : nodes) {
            if (!node.getChildren().isEmpty()) {
                sortTree(node.getChildren());
            }
        }
    }

    public List<T> getRoots() {
        return Collections.unmodifiableList(roots);
    }

    public T findNode(String id) {
        return nodeMap.get(id);
    }

    public List<T> getPathToNode(String id) {
        List<T> path = new ArrayList<>();
        T node = nodeMap.get(id);

        while (node != null) {
            path.add(0, node);
            String parentId = node.getParentId();
            node = parentId != null ? nodeMap.get(parentId) : null;
        }

        return path;
    }

    public List<T> getNodesAtLevel(int level) {
        return getNodesAtLevel(roots, level, 1);
    }

    private List<T> getNodesAtLevel(List<T> nodes, int targetLevel, int currentLevel) {
        List<T> result = new ArrayList<>();
        for (T node : nodes) {
            if (currentLevel == targetLevel) {
                result.add(node);
            } else if (currentLevel < targetLevel) {
                result.addAll(getNodesAtLevel(node.getChildren(), targetLevel, currentLevel + 1));
            }
        }
        return result;
    }

    public List<T> getNodes() {
        return new ArrayList<>(nodeMap.values());
    }

    /**
     * Returns all nodes in a flat list.
     */
    public List<T> toFlatList() {
        List<T> flatList = new ArrayList<>(nodeMap.values());
        if (comparator != null) {
            flatList.sort(comparator);
        }
        return flatList;
    }

    /**
     * Alias for toFlatList, for semantic clarity.
     */
    public List<T> getAllNodes() {
        return toFlatList();
    }

    /**
     * Returns all nodes in depth-first hierarchical order.
     * Parents come before children.
     */
    public List<T> getNodesInHierarchy() {
        List<T> result = new ArrayList<>();
        for (T root : roots) {
            traverseHierarchy(root, result);
        }
        return result;
    }

    private void traverseHierarchy(T node, List<T> result) {
        result.add(node);
        for (T child : node.getChildren()) {
            traverseHierarchy(child, result);
        }
    }

}