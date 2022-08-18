package com.poo0054.DataStructure.tree;

import lombok.Data;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/18 10:07
 */
@Data
public class TreeNode {
    private int data;
    private TreeNode left, right;
    private int height;

    public TreeNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "当前节点位置：" + data;
    }


    /**
     * 返回 以该结点为根结点的树的高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

}
