package com.person.kotlintest.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @anthor tr
 * @date 2021/5/19
 * @desc
 */
public class Code1 {

//    给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
//
//    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
//
//    你可以按任意顺序返回答案。

    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        for (int m = 0; m < nums.length; m++) {
            for (int n = m + 1; n < nums.length; n++) {
                if (nums[m] + nums[n] == target) {
                    return new int[]{m, n};
                }
            }
        }
        return new int[0];
    }

    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> hash = new HashMap<>();
        for (int m = 0; m < nums.length; m++) {
            if (hash.containsKey(target - nums[m])) {
                return new int[]{m, hash.get(target - nums[m])};
            } else {
                hash.put(nums[m], m);
            }
        }
        return new int[0];
    }
}
