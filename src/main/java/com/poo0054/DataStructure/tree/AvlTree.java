package com.poo0054.DataStructure.tree;

/**
 * 平衡二叉查找树 - val树
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/16 18:27
 */
public class AvlTree extends BstTree {

    @Override
    public boolean insertNodeTree(int key) {
        if (super.insertNodeTree(key)) {
            //TODO 需要旋转
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteNodeTree(int key) {
        if (super.deleteNodeTree(key)) {
            return true;
            //TODO 需要旋转
        }
        return false;
    }

}
