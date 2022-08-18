package com.poo0054.DataStructure.tree;

import org.junit.Test;

/**
 * 二叉查找树 - bst树
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/16 18:27
 */
public class TreeTest {


    @Test
    public void bstTreeTest() {
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
        bstTree.insertNodeTree(30);
        bstTree.insertNodeTree(20);
        bstTree.insertNodeTree(30);
        bstTree.insertNodeTree(4);
        bstTree.insertNodeTree(1);
        bstTree.deleteNodeTree(5);
       /* //前序遍历 - 递归
        bstTree.preorderTraversal(bstTree);
        //前序遍历 - 循环
        bstTree.whilePreorderTraversal(bstTree);*/

        /*//中序遍历 - 递归
        bstTree.inorderTraversal(bstTree);
        //中序遍历 - 循环
        bstTree.whileInorderTraversal(bstTree);*/
        bstTree.postorderTraversal(bstTree);
        bstTree.whilePostorderTraversal(bstTree);

    }

    @Test
    public void avlTreeTest() {
        AvlTree bstTree = new AvlTree();

    }


}
