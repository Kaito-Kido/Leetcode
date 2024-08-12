// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

class Solution {
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            min = Math.min(min, nums[i]);
        }

        return min;
    }
}