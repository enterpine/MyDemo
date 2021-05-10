package com.jinsong.leetcode;

public class LeetCode872 {

    private static class TreeNode implements Cloneable {
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

        @Override
        public Object clone() {
            TreeNode treeNode = null;
            try{
                treeNode = (TreeNode)super.clone();
            }catch(CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return treeNode;
        }
    }

    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        getLeafSer(root1,stringBuffer1);
        System.out.println("--");
        getLeafSer(root2,stringBuffer2);
        System.out.println("--");
        System.out.println(stringBuffer1.toString());

        System.out.println(stringBuffer2.toString());
        return stringBuffer1.toString().equals(stringBuffer2.toString());
    }

    private static void getLeafSer(TreeNode root,StringBuffer stringBuffer) {

        TreeNode left = root.left;
        TreeNode right = root.right;
        System.out.print(root.val);
        if(left != null) {
            getLeafSer(left,stringBuffer);
        }
        if(right != null) {
            getLeafSer(right,stringBuffer);
        }
        if(left == null && right == null){
            stringBuffer.append(root.val+",");
            return ;
        }

    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);

        TreeNode t1_node1 = (TreeNode)node1.clone();
        TreeNode t1_node2 = (TreeNode)node2.clone();
        TreeNode t1_node3 = (TreeNode)node3.clone();
        TreeNode t1_node4 = (TreeNode)node4.clone();
        TreeNode t1_node5 = (TreeNode)node5.clone();
        TreeNode t1_node6 = (TreeNode)node6.clone();
        TreeNode t1_node7 = (TreeNode)node7.clone();
        TreeNode t1_node8 = (TreeNode)node8.clone();
        TreeNode t1_node9 = (TreeNode)node9.clone();

        TreeNode t2_node1 = (TreeNode)node1.clone();
        TreeNode t2_node2 = (TreeNode)node2.clone();
        TreeNode t2_node3 = (TreeNode)node3.clone();
        TreeNode t2_node4 = (TreeNode)node4.clone();
        TreeNode t2_node5 = (TreeNode)node5.clone();
        TreeNode t2_node6 = (TreeNode)node6.clone();
        TreeNode t2_node7 = (TreeNode)node7.clone();
        TreeNode t2_node8 = (TreeNode)node8.clone();
        TreeNode t2_node9 = (TreeNode)node9.clone();

        t1_node3.left = t1_node5;
        t1_node3.right = t1_node1;
        t1_node5.left = t1_node6;
        t1_node5.right = t1_node2;
        t1_node1.left = t1_node9;
        t1_node1.right = t1_node8;
        t1_node2.left = t1_node7;
        t1_node2.right = t1_node4;

        t2_node3.left = t2_node5;
        t2_node3.right = t2_node1;
        t2_node5.left = t2_node6;
        t2_node5.right = t2_node7;
        t2_node1.left = t2_node4;
        t2_node1.right = t2_node2;
        t2_node2.left = t2_node9;
        t2_node2.right = t2_node8;


        System.out.println(leafSimilar(t1_node3,t2_node3));

    }
}
