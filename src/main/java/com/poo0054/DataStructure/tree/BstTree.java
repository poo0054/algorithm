package com.poo0054.DataStructure.tree;

import java.util.Stack;
import java.util.function.Consumer;

/**
 * 二叉查找树 - bst树
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/16 18:27
 */
public class BstTree {

    /**
     * 用来存储树
     */
    private TreeNode treeNode;

    protected TreeNode getTreeNode() {
        return treeNode;
    }

    /**
     * 返回指定树的高度
     */
    public int height(TreeNode treeNode) {
        TreeNode node = treeNode;
        if (null == node) {
            return 0;
        }
        return node.height();
    }

    /**
     * 返回左子树的高度
     */
    public int leftHeight() {
        if (null == this.treeNode) {
            return 0;
        }
        if (null == this.treeNode.getLeft()) {
            return 1;
        }
        return this.treeNode.getLeft().height();
    }


    /**
     * 返回右子树的高度
     */
    public int rightHeight() {
        if (null == this.treeNode) {
            return 0;
        }
        if (null == this.treeNode.getRight()) {
            return 1;
        }
        return this.treeNode.getRight().height();
    }


    /**
     * 后序遍历----循环
     */
    public void whilePostorderTraversal(BstTree bstTree) {
        System.out.printf("后序遍历（循环）：");
        TreeNode currentNode = bstTree.treeNode;
        Stack<TreeNode> nodeStack = new Stack<>();
        TreeNode consumeNode = null;
        //左  - 右  -  中
        while (null != currentNode || !nodeStack.isEmpty()) {
            //当前节点不为空
            if (null != currentNode) {
                //往右继续往下找
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeft();
            } else {
                //从栈中获取
                TreeNode node = nodeStack.pop();
                TreeNode right = node.getRight();
                if (null == right) {
                    //右边为空 输出节点
                    System.out.printf("%s \t", node);
                    consumeNode = node;
                } else {
                    //右边有值，有2种情况 一直是右节点没有节点了 右节点的右节点都被消费了 只需要保存最后一次消费的值等于右节点的右节点表示右节点的所有节点全部被消费过了
                    //如果右边的值是上次消费的  就表示右边所有值全部都已经输出完毕
                    if (consumeNode == right) {
                        consumeNode = node;
                        System.out.printf("%s \t", node);
                    } else {
                        //右边有值  重新加入栈
                        nodeStack.push(node);
                        currentNode = right;
                    }

                }

            }

        }
    }

    /**
     * 后序遍历----递归
     */
    public void postorderTraversal(BstTree bstTree) {
        System.out.printf("后序遍历（递归）：");
        postorderPrint(bstTree.treeNode);
        System.out.println();
    }

    private void postorderPrint(TreeNode currentNode) {
        if (null != currentNode) {
            //遍历左边
            postorderPrint(currentNode.getLeft());
            //编译右边
            postorderPrint(currentNode.getRight());
            //输出当前节点
            System.out.printf("%s \t", currentNode);
        }
    }

    /**
     * 中序遍历----循环
     */
    public void whileInorderTraversal(BstTree bstTree) {
        System.out.printf("中序遍历（循环）：");
        TreeNode currentNode = bstTree.treeNode;
        Stack<TreeNode> nodeStack = new Stack<>();
        while (null != currentNode || !nodeStack.isEmpty()) {
            //先输出左边 然后中间 最后右边
            //当前不为空
            if (null != currentNode) {
                //只有当前节点的左节点为空才输出
                TreeNode left = currentNode.getLeft();
                //只有在左右都为空的情况下才输出当前节点值
                if (null == left && null == currentNode.getRight()) {
                    //左边为空 需要看看右边的值
                    System.out.printf("%s \t", currentNode);
                } else {
                    //左右节点有一个不为空就加入栈
                    nodeStack.push(currentNode);
                }
                currentNode = left;
            } else {
                //处理栈中
                TreeNode node = nodeStack.pop();
                //输出中间节点
                System.out.printf("%s \t", node);
                //右边不为空 处理右边
                currentNode = node.getRight();
            }
        }
        System.out.println();
    }


    /**
     * 中序遍历----递归
     */
    public void inorderTraversal(BstTree bstTree) {
        System.out.printf("中序遍历（递归）：");
        inorderPrint(bstTree.treeNode);
        System.out.println();
    }

    private void inorderPrint(TreeNode currentNode) {
        if (null != currentNode) {
            //遍历左边
            inorderPrint(currentNode.getLeft());
            //输出当前节点
            System.out.printf("%s \t", currentNode);
            //编译右边
            inorderPrint(currentNode.getRight());
        }
    }

    /**
     * 前序遍历----循环
     */
    public void whilePreorderTraversal(BstTree bstTree, Consumer<TreeNode> consumer) {
        System.out.printf("前序遍历（循环）：");
        //当前操作元素
        TreeNode currentNode = bstTree.treeNode;
        //右节点加入栈 先进后出
        Stack<TreeNode> nodeStack = new Stack<>();
        while (null != currentNode || !nodeStack.isEmpty()) {
            //当前节点为空了 该操作栈中节点
            if (null == currentNode) {
                //操作右边元素
                currentNode = nodeStack.pop();
            } else {
                //当前节点不为空 当前节点的右节点不为空 把右节点加入栈
                if (null != currentNode.getRight()) {
                    nodeStack.push(currentNode.getRight());
                }
                //输出当前节点
                System.out.printf("%s \t", currentNode);
                if (null != consumer) {
                    consumer.accept(currentNode);
                }

                //当前操作元素为左边
                currentNode = currentNode.getLeft();
            }
        }
        System.out.println();
    }

    /**
     * 前序遍历----循环
     */
    public void whilePreorderTraversal(BstTree bstTree) {
        whilePreorderTraversal(bstTree, null);
    }

    /**
     * 前序遍历----递归
     */
    public void preorderTraversal(BstTree bstTree) {
        System.out.printf("前序遍历（递归）：");
        preorderPrint(bstTree.treeNode);
        System.out.println();
    }

    private void preorderPrint(TreeNode currentNode) {
        if (null != currentNode) {
            //输出当前节点
            System.out.printf("%s \t", currentNode);
            //遍历左边
            preorderPrint(currentNode.getLeft());
            //编译右边
            preorderPrint(currentNode.getRight());
        }
    }


    /**
     * 根据key查找树节点
     *
     * @return 查找到的树节点
     */
    public TreeNode getNodeTree(int key) {
        //当前操作的节点
        TreeNode currentNode = this.treeNode;
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
        TreeNode insertData = new TreeNode(key);
        //当前操作的节点
        TreeNode currentNode = this.treeNode;
        //root节点不存在
        if (null == currentNode) {
            this.treeNode = insertData;
            return true;
        }
        //root节点存在
        while (true) {
            //相同的值插入失败
            if (key == currentNode.getData()) {
                return false;
            }

            if (key < currentNode.getData()) {
                //左边
                TreeNode leftNode = currentNode.getLeft();
                if (null == leftNode) {
                    //右边不存在 新增
                    currentNode.setLeft(insertData);
                    return true;
                }
                currentNode = leftNode;
            } else {
                //右边
                TreeNode rightNode = currentNode.getRight();
                if (null == rightNode) {
                    //右边不存在 新增
                    currentNode.setRight(insertData);
                    return true;
                }
                currentNode = rightNode;
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
        TreeNode currentNode = this.treeNode;
        //父个节点
        TreeNode parentNode = null;
        if (null == currentNode) {
            return false;
        }
        //0表示左边 1表示右边
        int direction = 0;
        while (null != currentNode) {
            int data = currentNode.getData();
            //如果删除的元素是自己
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
                TreeNode changeNode = getPretreatmentNode(currentNode);
                //把该节点替换的左节点和右节点替换就好了
                //把子节点添加给该节点
                changeNode.setLeft(currentNode.getLeft());
                changeNode.setRight(currentNode.getRight());
                return doChangeNode(parentNode, direction, changeNode);
            }
            //父节点
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


    private TreeNode getPretreatmentNode(TreeNode currentNode) {
        //需要处理的节点
        TreeNode changeNode = currentNode.getRight();
        while (null != changeNode.getLeft()) {
            changeNode = changeNode.getLeft();
        }
        //删除节点
        deleteNodeTree(changeNode.getData());
        return changeNode;
    }

    private boolean partChangeNode(TreeNode CurrentNode, TreeNode parentNode, int direction) {
        //需要改变的值  如果都是空就设置空
        TreeNode changeNode = null;
        //左节点不为空
        if (null != CurrentNode.getLeft()) {
            changeNode = CurrentNode.getLeft();
        }

        //右节点不为空
        if (null != CurrentNode.getRight()) {
            changeNode = CurrentNode.getRight();
        }

        return doChangeNode(parentNode, direction, changeNode);
    }

    private boolean doChangeNode(TreeNode parentNode, int direction, TreeNode changeNode) {
        //不是根节点
        if (null != parentNode) {
            if (direction == 0) {
                parentNode.setLeft(changeNode);
                return true;
            }
            //右边
            parentNode.setRight(changeNode);
        } else {
            //替换为根节点
            this.treeNode = changeNode;
        }
        return true;
    }


}
