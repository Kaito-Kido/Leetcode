// https://leetcode.com/problems/reverse-subarray-to-maximize-array-value/

// Complexity:
// Time: O(N)
// Space: O(1)

class Solution {
    private int getDiff(int a, int b) {
        return Math.abs(a - b);
    }

    public int maxValueAfterReverse(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            res += getDiff(nums[i], nums[i + 1]);

            max = Math.max(max, Math.min(nums[i], nums[i + 1]));
            min = Math.min(min, Math.max(nums[i], nums[i + 1]));
        }

        int diff = Math.max(0, 2 * (max - min));
        for (int i = 0; i < nums.length - 1; i++) {
            diff = Math.max(diff, 
                getDiff(nums[0], nums[i + 1]) - getDiff(nums[i], nums[i + 1]));
        }
        for (int i = 0; i < nums.length - 1; i++) {
            diff = Math.max(diff, 
                getDiff(nums[nums.length - 1], nums[i]) - getDiff(nums[i], nums[i + 1]));
        }
        return res + diff;
    }
}