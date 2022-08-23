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


    /**
     * 返回 以该结点为根结点的树的高度
     */
    public int height() {
        return Math.max(null == left ? 1 : left.height(), null == right ? 1 : right.height()) + 1;
    }

    /**
     * 返回左子树的高度
     */
    public int leftHeight() {
        if (null == left) {
            return 1;
        }
        return left.height();
    }

    /**
     * 返回右子树的高度
     */
    public int rightHeight() {
        if (null == right) {
            return 1;
        }
        return right.height();
    }

}
