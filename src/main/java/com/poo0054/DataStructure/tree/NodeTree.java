package com.poo0054.DataStructure.tree;

import lombok.Data;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/18 10:07
 */
@Data
public class NodeTree {
    int data;
    NodeTree left, right;

    public NodeTree(int data) {
        this.data = data;
    }
}
