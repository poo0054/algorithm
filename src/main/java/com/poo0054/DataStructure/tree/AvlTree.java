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
            //左边比右边高  右旋
            if (leftHeight() - rightHeight() > 1) {
                //右旋
                rightRevolve();
            } else {
                //左旋
                leftRevolve();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteNodeTree(int key) {
        if (super.deleteNodeTree(key)) {
            //左边比右边高  右旋
            if (leftHeight() - rightHeight() > 1) {
                //右旋
                rightRevolve();
            } else {
                //左旋
                leftRevolve();

            }
            return true;
        }
        return false;
    }

    /**
     * 左旋
     */
    protected void leftRevolve() {

    }

    /**
     * 右旋
     */
    protected void rightRevolve() {
        TreeNode treeNode = getTreeNode();
        //取出源根节点所有右节点
        TreeNode left = treeNode.getLeft();
        //TODO 右旋

    }

}


