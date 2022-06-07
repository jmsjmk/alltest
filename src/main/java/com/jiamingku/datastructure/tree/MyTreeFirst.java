package com.jiamingku.datastructure.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基于数组来实现的,相对来说是非常简单的一棵树(非二叉的)
 * 这个就是节点挂放在数组中可以是任意的位置.
 *
 * @param <T>
 */
public class MyTreeFirst<T> {
    private final int DEFAULT_SIZE = 1000;
    private int size;
    private int count;
    private Object[] nodes;

    public MyTreeFirst(MyNode<T> root) {
        this.size = this.DEFAULT_SIZE;
        this.nodes = new Object[this.size];
        this.count = 1;
        this.nodes[0] = root;
    }

    // 添加一个节点在当前数组中，任何位置都可以放置，用数据存放，在那个位置都可以放上就可以了
    public void add(MyNode<T> node) {
        for (int i = 0; i < this.size; i++) {
            if (this.nodes[i] == null) {
                nodes[i] = node;
                break;
            }
        }
        this.count++;
    }

    public void check() {
        if (this.count >= this.size) {
            this.enlarge();
        }
    }

    // 添加一个节点，并指明父节点
    public void add(MyNode<T> node, MyNode<T> parent) {
        this.check();
        node.setParentIndex(this.position(parent));
        this.add(node);
    }

    // 获取节点在数组的存储位置
    public int position(MyNode<T> node) {
        for (int i = 0; i < this.size; i++) {
            if (nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }

    //获取整棵树有多少节点  
    public int getSize() {
        return this.count;
    }

    //获取根节点  
    @SuppressWarnings("unchecked")
    public MyNode<T> getRoot() {
        return (MyNode<T>) this.nodes[0];
    }

    //获取所以节点，以List形式返回  
    @SuppressWarnings("unchecked")
    public List<MyNode<T>> getAllNodes() {
        List<MyNode<T>> list = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            if (this.nodes[i] != null) {
                list.add((MyNode<T>) nodes[i]);
            }
        }
        return list;
    }

    // 获取树的深度，只有根节点时为1
    @SuppressWarnings("unchecked")
    public int getDepth() {
        int max = 1;
        if (this.nodes[0] == null) {
            return 0;
        }
        for (int i = 0; i < this.count; i++) {
            int deep = 1;
            int location = ((MyNode<T>) (this.nodes[i])).getParentIndex();
            while (location != -1 && this.nodes[location] != null) {
                location = ((MyNode<T>) (this.nodes[location])).getParentIndex();
                deep++;
            }
            if (max < deep) {
                max = deep;
            }
        }
        return max;
    }

    public void enlarge() {
        this.size = this.size + this.DEFAULT_SIZE;
        Object[] newNodes = Arrays.copyOf(nodes, this.size);
        this.nodes = newNodes;
    }


    public static void main(String[] args) {
        MyNode<String> root = new MyNode<>("A", -1);
        MyTreeFirst<String> tree = new MyTreeFirst<>(root);
        MyNode<String> b = new MyNode<>("B");
        MyNode<String> c = new MyNode<>("C");
        MyNode<String> d = new MyNode<>("D");
        MyNode<String> e = new MyNode<>("E");
        MyNode<String> f = new MyNode<>("F");
        MyNode<String> g = new MyNode<>("G");
        tree.add(b, root);
        tree.add(c, root);
        tree.add(d, root);

        tree.add(e, b);
        tree.add(f, b);
        tree.add(g, f);

        System.out.println(tree.getSize());
        System.out.println(tree.getRoot().getData());
        System.out.println(tree.getAllNodes());
        System.out.println(tree.getDepth());
        tree.add(new MyNode<String>("H"), g);
        System.out.println(tree.getDepth());
        tree.enlarge();
    }
}

class MyNode<T> {
    private T data;
    private int parentIndex;

    public MyNode(T data) {
        this.data = data;
    }

    public MyNode(T data, int parentIndex) {
        this.data = data;
        this.parentIndex = parentIndex;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public void setParentIndex(int parentIndex) {
        this.parentIndex = parentIndex;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Node{");
        sb.append("data=").append(data);
        sb.append(", parentIndex=").append(parentIndex);
        sb.append('}');
        return sb.toString();
    }
}
