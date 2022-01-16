package com.algorithm.homework.weekthree.p210;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author qiuch
 * 参考课堂课程表（P207）
 * 构建邻接链表，从0度的节点出发是否为有向无环图
 * 增加记录课程序列
 * Time complexity : O(V+E) （点数+边数）
 * Space complexity : O(V+E)
 */
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];
        // 建立入度表,计算有几门先修课
        for (int[] prerequisite : prerequisites) {
            inDegrees[prerequisite[0]]++;
        }
        // 入度为0的节点进入队列，开始BFS
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int canFinishCount = 0;
        int[] result = new int[numCourses];
        // 根据提供的先修课列表，删除入度为 0 的节点
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            result[canFinishCount++] = currentCourse;
            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] == currentCourse) {
                    --inDegrees[prerequisite[0]];
                    if (inDegrees[prerequisite[0]] == 0) {
                        queue.offer(prerequisite[0]);
                    }
                }
            }
        }
        if (canFinishCount == numCourses) {
            return result;
        }
        return new int[0];
    }
}

