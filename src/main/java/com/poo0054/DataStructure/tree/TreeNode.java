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

    public TreeNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "当前节点位置：" + data;
    }


}
