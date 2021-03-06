package com.jiamingku.datastructure.tree;

/**
 * 二叉数，由节点构成,非平衡的树(高度差随意)
 */
public class BinaryTree {
    private BNode root; //根节点  

    public BinaryTree() {
        root = null;
    }

    // 二叉搜索树查找的时间复杂度为O(logN)
    public BNode find(int key) { //find node with given key  
        BNode current = root;
        while (current.key != key) {
            if (key < current.key) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    //插入节点  
    public void insert(int key, double value) {
        BNode newNode = new BNode(key);
        if (root == null) { //if tree is null
            root = newNode;
        } else {
            BNode current = root;
            BNode parent;
            while (true) {
                parent = current;
                if (key < current.key) { //turn left
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        newNode.parent = parent;
                        return;
                    }
                } else { //turn right
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        newNode.parent = parent;
                        return;
                    }
                }
            }
        }
    }

    //遍历二叉树  
    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.println("Preorder traversal:");
                preOrder(root);//前向遍历  
                break;
            case 2:
                System.out.println("Inorder traversal:");
                inOrder(root);//中向遍历  
                break;
            case 3:
                System.out.println("Postorder traversal:");
                postOrder(root);//后向遍历  
                break;
            default:
                System.out.println("Inorder traversal:");
                inOrder(root);
                break;
        }
        System.out.println("");
    }

    //前向遍历  
    private void preOrder(BNode localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.key + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    //中向遍历  
    private void inOrder(BNode localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.key + " ");
            inOrder(localRoot.rightChild);
        }
    }

    //后向遍历  
    private void postOrder(BNode localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.key + " ");
        }
    }

    //查找最小值  
    /*根据二叉搜索树的存储规则，最小值应该是左边那个没有子节点的那个节点*/
    public BNode minNumber() {
        BNode current = root;
        BNode parent = root;
        while (current != null) {
            parent = current;
            current = current.leftChild;
        }
        return parent;
    }

    //查找最大值  
    /*根据二叉搜索树的存储规则，最大值应该是右边那个没有子节点的那个节点*/
    public BNode maxNumber() {
        BNode current = root;
        BNode parent = root;
        while (current != null) {
            parent = current;
            current = current.rightChild;
        }
        return parent;
    }

    //删除节点  
    /*
     * 删除节点在二叉树中是最复杂的，主要有三种情况：
     * 1. 该节点没有子节点（简单）
     * 2. 该节点有一个子节点（还行）
     * 3. 该节点有两个子节点（复杂）
     * 删除节点的时间复杂度为O(logN)
     */
    public boolean delete(int key) {
        BNode current = root;
        boolean isLeftChild = true;
        if (current == null) {
            return false;
        }
        // 寻找要删除的节点,删除的节点一定要知道他原来的左孩子还是右孩子，非常重要
        while (current.key != key) {
            if (key < current.key) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null) {
                return false;
            }
        }

        //找到了要删除的节点，下面开始删除  
        //1. 要删除的节点没有子节点,直接将其父节点的左子节点或者右子节点赋为null即可  
        if (current.leftChild == null && current.rightChild == null) {
            return deleteNoChild(current, isLeftChild);
        }

        //3. 要删除的节点有两个子节点  
        else if (current.leftChild != null && current.rightChild != null) {
            return deleteTwoChild(current, isLeftChild);
        }

        //2. 要删除的节点有一个子节点，直接将其砍断，将其子节点与其父节点连起来即可，要考虑特殊情况就是删除根节点，因为根节点没有父节点  
        else {
            return deleteOneChild(current, isLeftChild);
        }
    }

    public boolean deleteNoChild(BNode node, boolean isLeftChild) {
        if (node == root) {
            root = null;
            return true;
        }
        if (isLeftChild) {
            node.parent.leftChild = null;
        } else {
            node.parent.rightChild = null;
        }
        return true;
    }

    public boolean deleteOneChild(BNode node, boolean isLeftChild) {
        if (node.leftChild == null) { // 当前节点有右孩子
            if (node == root) {
                root = node.rightChild;
                node.parent = null;
                return true;
            }
            if (isLeftChild) {
                node.parent.leftChild = node.rightChild;
            } else {
                node.parent.rightChild = node.rightChild;
            }
            node.rightChild.parent = node.parent;
        } else {
            if (node == root) {
                root = node.leftChild;
                node.parent = null;
                return true;
            }
            if (isLeftChild) {
                node.parent.leftChild = node.leftChild;
            } else {
                node.parent.rightChild = node.leftChild;
            }
            node.leftChild.parent = node.parent;
        }
        return true;
    }

    public boolean deleteTwoChild(BNode node, boolean isLeftChild) {
        BNode successor = getSuccessor(node);
        if (node == root) {
            successor.leftChild = root.leftChild;
            successor.parent = null;
            root = successor;
            if (root.rightChild != successor) {
                successor.rightChild = root.rightChild;
            }
        } else if (isLeftChild) {
            node.parent.leftChild = successor;
        } else {
            node.parent.rightChild = successor;
        }
        successor.leftChild = node.leftChild;//connect successor to node's left child  
        return true;
    }

    // 获得要删除节点的后继节点（中序遍历的下一个节点）
    public BNode getSuccessor(BNode delNode) {
        BNode successor = delNode;
        BNode current = delNode.rightChild;
        while (current != null) {
            successor = current;
            current = current.leftChild;
        }
        if (successor != delNode.rightChild) {
            successor.parent.leftChild = successor.rightChild;
            if (successor.rightChild != null) {
                successor.rightChild.parent = successor.parent;//删除后续节点在原来的位置
            }
            successor.rightChild = delNode.rightChild;//将后续节点放到正确位置，与右边连上
        }
        return successor;
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(100, 100.0);
        binaryTree.insert(30, 30.0);
        binaryTree.insert(110, 110.0);

        binaryTree.traverse(2);

        boolean b = binaryTree.delete(100);
        System.out.println("b = " + b);
        binaryTree.traverse(2);
    }
}


/**
 * 树肯定是由节点构成：所有节点的类型必须是相同的，并且当前节点应该可以找到对应的父，左右，数据关系
 */
class BNode {
    public BNode(int key) {
        this.key = key;
    }

    public int key;

    public BNode parent;
    public BNode leftChild;
    public BNode rightChild;

    public void displayNode() {
        System.out.println("key:" + key);
    }
}  