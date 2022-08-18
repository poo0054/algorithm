package com.poo0054.DataStructure.tree;

import org.junit.Test;

/**
 * 二叉查找树 - bst树
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/16 18:27
 */
public class BstTree {

    @Test
    public void test() {
        BstTree bstTree = new BstTree();
        bstTree.insertNodeTree(5);
        bstTree.insertNodeTree(10);
        bstTree.insertNodeTree(3);
        bstTree.insertNodeTree(15);
        bstTree.insertNodeTree(7);
        bstTree.insertNodeTree(8);
        bstTree.insertNodeTree(6);
        bstTree.insertNodeTree(13);
        bstTree.insertNodeTree(14);
        bstTree.insertNodeTree(12);
        //删除节点
        bstTree.deleteNodeTree(10);
        NodeTree nodeTree1 = bstTree.getNodeTree(15);
    }

    /**
     * 用来存储树
     */
    private NodeTree nodeTree;

    /**
     * 根据key查找树节点
     *
     * @return 查找到的树节点
     */
    public NodeTree getNodeTree(int key) {
        //当前操作的节点
        NodeTree currentNode = this.nodeTree;
        //树不为空
        while (currentNode != null && currentNode.getData() != key) {
            //根节点的值
            int data = currentNode.getData();
            if (key > data) {
                //找右边
                currentNode = currentNode.getRight();
            } else {
                //找左边
                currentNode = currentNode.getLeft();
            }
        }
        return currentNode;
    }

    /**
     * 插入一个数节点
     *
     * @return 是否成功
     */
    public boolean insertNodeTree(int key) {
        NodeTree insertData = new NodeTree(key);
        //当前操作的节点
        NodeTree root = this.nodeTree;
        //root节点不存在
        if (null == root) {
            this.nodeTree = insertData;
            return true;
        }

        //root节点存在
        while (true) {
            //相同的值插入失败
            if (key == root.getData()) {
                return false;
            }

            if (key < root.getData()) {
                //左边
                NodeTree leftNode = root.getLeft();
                if (null == leftNode) {
                    //右边不存在 新增
                    root.setLeft(insertData);
                    return true;
                }
                root = leftNode;
            } else {
                //右边
                NodeTree rightNode = root.getRight();
                if (null == rightNode) {
                    //右边不存在 新增
                    root.setRight(insertData);
                    return true;
                }
                root = rightNode;

            }
        }
    }

    /**
     * 删除一个元素
     *
     * @param key 需要删除的元素
     * @return 是否成功删除
     */
    public boolean deleteNodeTree(int key) {
        //当前操作的元素
        NodeTree currentNode = this.nodeTree;
        //上个节点
        NodeTree parentNode = null;
        if (null == currentNode) {
            return false;
        }

        //0表示左边 1表示右边
        int direction = 0;
        while (null != currentNode) {
            //如果删除的元素是自己
            int data = currentNode.getData();
            if (key == data) {
                //删除逻辑

                //只有一个节点或者没有节点
                if (null == currentNode.getLeft() || null == currentNode.getRight()) {
                    //部分改变
                    return partChangeNode(currentNode, parentNode, direction);
                }

                //俩边都不为空
                //获取右边最小的一个数或者左边最大的数
                //获取右边最小的节点 并把该节点从右边删除  ps:当前节点右边最小的数(右边的最左边最小的数)  说明该节点的左节点不存在 直接调用deleteNodeTree方法进行删除
                NodeTree pretreatmentNode = getPretreatmentNode(currentNode);
                //把该节点替换的左节点和右节点替换就好了
                return replaceNode(currentNode, parentNode, direction, pretreatmentNode);
            }
            parentNode = currentNode;
            if (key > data) {
                //右边
                currentNode = currentNode.getRight();
                direction = 1;
            } else {
                //左边
                currentNode = currentNode.getLeft();
                direction = 0;
            }
        }
        return false;
    }

    private boolean replaceNode(NodeTree currentNode, NodeTree parentNode, int direction, NodeTree pretreatmentNode) {
        //把子节点添加给该节点
        pretreatmentNode.setLeft(currentNode.getLeft());
        pretreatmentNode.setRight(currentNode.getRight());
        //不是父节点
        if (null != parentNode) {
            if (direction == 0) {
                //左边
                parentNode.setLeft(pretreatmentNode);
            } else {
                parentNode.setRight(pretreatmentNode);
            }
            return true;
        } else {
            //是父节点
            this.nodeTree = pretreatmentNode;
            return true;
        }
    }

    private NodeTree getPretreatmentNode(NodeTree node) {
        //当前处理的节点
        NodeTree CurrentNode = node.getRight();
        while (null != CurrentNode.getLeft()) {
            CurrentNode = CurrentNode.getLeft();
        }
        //找到该节点 并删除
        if (deleteNodeTree(CurrentNode.getData())) {
            CurrentNode.setRight(null);
            CurrentNode.setLeft(null);
            return CurrentNode;
        }

        return null;
    }

    private boolean partChangeNode(NodeTree CurrentNode, NodeTree parent, int direction) {
        //需要改变的值  如果都是空就设置空
        NodeTree changeNode = null;
        //左节点不为空
        if (null != CurrentNode.getLeft()) {
            changeNode = CurrentNode.getLeft();
        }

        //右节点不为空
        if (null != CurrentNode.getRight()) {
            changeNode = CurrentNode.getRight();
        }

        //不是根节点
        if (null != parent) {
            if (direction == 0) {
                parent.setLeft(changeNode);
                return true;
            }
            parent.setRight(changeNode);
            return true;
        } else {
            //没有父节点 表示根节点
            this.nodeTree = changeNode;
            return true;
        }
    }


}
