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
            return balance();
        }
        return false;
    }

    private boolean balance() {
        TreeNode node = this.getTreeNode();
        //左边比右边高
        if (leftHeight() - rightHeight() > 1) {
            //问题在左子数的左边还是右边
            TreeNode left = node.getLeft();
            if (left.leftHeight() - left.rightHeight() >= 0) {
                //问题出在左节点的左边 ll
                //右旋
//                rightRevolve(this.getTreeNode());
            } else {
                //问题出在左节点的右边  -- lr  先左旋
                //先把左节点左旋
                node.setLeft(leftRevolve(left));
            }
            node = rightRevolve(node);
        }

        if (rightHeight() - leftHeight() > 1) {
            //右边比左边高
            //问题在左子数的左边还是右边
            TreeNode right = node.getRight();
            if (right.rightHeight() - right.leftHeight() >= 0) {
                //问题在右节点的右边 -- rr
//                leftRevolve(this.getTreeNode());
            } else {
                //问题出在右节点的左边  -- rl 先右旋
                node.setRight(rightRevolve(right));
            }
            //左旋
            node = leftRevolve(node);
        }
        super.setTreeNode(node);
        return true;
    }

    @Override
    public boolean deleteNodeTree(int key) {
        if (super.deleteNodeTree(key)) {
            return balance();
        }
        return false;
    }

    /**
     * 左旋
     */
    protected TreeNode leftRevolve(TreeNode treeNode) {
        System.out.println(treeNode + "===开始左旋");
        //根节点
        TreeNode right = treeNode.getRight();
        treeNode.setRight(right.getLeft());
        right.setLeft(treeNode);
        return right;
    }

    /**
     * 右旋
     */
    protected TreeNode rightRevolve(TreeNode treeNode) {
        System.out.println(treeNode + "===开始右旋");
        //使用当前节点做主节点
        TreeNode left = treeNode.getLeft();
        treeNode.setLeft(left.getRight());
        //  设置为当前根节点的右节点
        left.setRight(treeNode);
        return left;
    }

}


