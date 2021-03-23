package com.jinsong.leetcode;

import java.util.ArrayList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class LeetCode662_pending {

//    ArrayList<Integer> deepSearchLeft(TreeNode root){
//        if(root.left <> null){
//            deepSearchLeft(root);
//        }
//
//    }

    public static  int widthOfBinaryTree(TreeNode root) {
//        ArrayList<Integer> arrayListLeft = deepSearchLeft(root);
//        //ArrayList<Integer> arrayListERight = deepSearchRight(root);


        return 0;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode3_1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2);

        treeNode3_1.left = treeNode5;
        treeNode3_1.right = treeNode3;
        treeNode2.right = treeNode9;
        root.left = treeNode3_1;
        root.right = treeNode2;

        widthOfBinaryTree(root);
    }
}
