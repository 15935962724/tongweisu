package com.tongweisu.entity;

import java.util.ArrayList;
import java.util.List;

public class TWS_Category {
    private Long id;

    private String name;

    private String treePath;

    private Long orders;

    private Long parent;

    private List<TWS_Category> childNodes = new ArrayList<>();

    public List<TWS_Category> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<TWS_Category> childNodes) {
        this.childNodes = childNodes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath == null ? null : treePath.trim();
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }
}