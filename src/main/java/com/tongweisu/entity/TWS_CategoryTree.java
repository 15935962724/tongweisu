package com.tongweisu.entity;

import java.util.ArrayList;
import java.util.List;

public class TWS_CategoryTree {
    /**
     * 两层循环实现建树
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public static List<TWS_Category> bulid(List<TWS_Category> treeNodes) {

        List<TWS_Category> trees = new ArrayList<TWS_Category>();

        for (TWS_Category treeNode : treeNodes) {

            if (treeNode.getParent()==null) {
                trees.add(treeNode);
            }

            for (TWS_Category it : treeNodes) {
                if (it.getParent() == treeNode.getId()) {
                    if (treeNode.getChildNodes() == null) {
                        treeNode.setChildNodes(new ArrayList<TWS_Category>());
                    }
                    treeNode.getChildNodes().add(it);
                }
            }
        }
        return trees;
    }

    /**
     * 使用递归方法建树
     * @param treeNodes
     * @return
     */
    public static List<TWS_Category> buildByRecursive(List<TWS_Category> treeNodes) {
        List<TWS_Category> trees = new ArrayList<TWS_Category>();
        for (TWS_Category treeNode : treeNodes) {
            if (treeNode.getParent()==null) {
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static TWS_Category findChildren(TWS_Category treeNode,List<TWS_Category> treeNodes) {
        for (TWS_Category it : treeNodes) {
            if (treeNode.getId().equals(it.getParent())) {
                if (treeNode.getChildNodes() == null) {
                    treeNode.setChildNodes(new ArrayList<TWS_Category>());
                }
                treeNode.getChildNodes().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;

    }

}




