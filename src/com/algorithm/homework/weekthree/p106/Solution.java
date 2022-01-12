package com.algorithm.homework.weekthree.p106;

import java.util.HashMap;

class Solution {
    int[] postOrder;
    //记住中序遍历数组的下标,不用循环找中序下标
    HashMap<Integer, Integer> inOrderIndex = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postOrder = postorder;
        for (int i = 0; i < inorder.length; i++) {
            inOrderIndex.put(inorder[i], i);
        }
        return buildSubTree(0, inorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode buildSubTree(int inOrderStart, int inOrederEnd, int postOrderStart, int postOrderEnd) {
        if (inOrederEnd < inOrderStart || postOrderEnd < postOrderStart) {
            return null;
        }

        int rootVal = postOrder[postOrderEnd];
        int inOrderRootIndex = inOrderIndex.get(rootVal);

        TreeNode node = new TreeNode(rootVal);
        node.left = buildSubTree(inOrderStart, inOrderRootIndex - 1, postOrderStart, postOrderStart + inOrderRootIndex - inOrderStart - 1);
        node.right = buildSubTree(inOrderRootIndex + 1, inOrederEnd, postOrderStart + inOrderRootIndex - inOrderStart, postOrderEnd - 1);
        return node;
    }


    public static void main(String[] args) {
        TreeNode result = new Solution().buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
        System.out.println(result);
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            StringBuilder nodeInfo = new StringBuilder();
            nodeInfo.append("TreeNode{");
            nodeInfo.append("val=").append(val);
            if (left != null) {
                nodeInfo.append(", left=").append(left.val).append(":");
                nodeInfo.append("[").append(left).append("]");
            } else {
                nodeInfo.append(", left=[null]");
            }

            if (left != null) {
                nodeInfo.append(", right=").append(right.val).append(":");
                nodeInfo.append("[").append(right).append("]");
            } else {
                nodeInfo.append(", right=[null]");
            }

            nodeInfo.append("}");
            return nodeInfo.toString();
        }
    }
}