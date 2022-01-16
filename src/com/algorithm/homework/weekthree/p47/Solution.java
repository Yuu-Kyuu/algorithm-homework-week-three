package com.algorithm.homework.weekthree.p47;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author qiuch
 * 回溯+剪枝（重复元素）
 * Time complexity : O(n×n!)
 * Space complexity : O(n)
 */
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        //排序后才能剪枝
        Arrays.sort(nums);
        dfs(nums, 0, used, new LinkedList<>(), result);
        return result;
    }

    //深度优先遍历状态树
    private void dfs(int[] nums, int depth, boolean[] used, LinkedList<Integer> path, List<List<Integer>> res) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            //   剪枝条件，使用过 或者 连续两值相等（排序的作用）且之前没用过（后续还会遍历到）
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, depth + 1, used, path, res);
            //回溯使用状态
            used[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        new Solution().permuteUnique(new int[]{1, 1, 2});
    }
}