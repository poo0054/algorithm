package com.poo0054.DataStructure.tree;

import org.junit.Test;

import java.util.Random;

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
        //前序遍历 - 递归
        bstTree.preorderTraversal(bstTree);
        //前序遍历 - 循环
        bstTree.whilePreorderTraversal(bstTree);
        //中序遍历 - 递归
        bstTree.inorderTraversal(bstTree);
        //中序遍历 - 循环
        bstTree.whileInorderTraversal(bstTree);
        bstTree.postorderTraversal(bstTree);
        bstTree.whilePostorderTraversal(bstTree);
    }

    @Test
    public void avlTreeTest() {
        System.out.println(avlTree);
    }


    private static BstTree bstTree = new BstTree();
    private static AvlTree avlTree = new AvlTree();

    static {
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
        bstTree.deleteNodeTree(40);
        bstTree.insertNodeTree(50);
        bstTree.insertNodeTree(60);
        bstTree.insertNodeTree(70);

        avlTree.insertNodeTree(20);
        avlTree.insertNodeTree(70);
        avlTree.insertNodeTree(84);
        avlTree.insertNodeTree(46);
        avlTree.insertNodeTree(38);
        avlTree.insertNodeTree(50);
        avlTree.insertNodeTree(55);
        avlTree.insertNodeTree(60);
    }

    public void init() {
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int anInt = random.nextInt(100);
            if (!avlTree.insertNodeTree(random.nextInt(100))) {
                System.out.printf("当前值：%s重复 添加失败 \n", anInt);
            }
            System.out.printf("第%s次添加的值为%s \t", i, anInt);
        }
    }

}
