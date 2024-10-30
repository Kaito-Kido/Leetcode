// Complexity:
// Time: O(n^2)
// Space: O(n)
// Solution:
// Using LIS and LDS pattern to find the longest subsequence at each index. The result is the minus of original array length with the longest subsequence (lis + lds).

class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;

        int[] lis = new int[n];
        for (int i = 0; i < n; i++) {
            lis[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        int[] lds = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            lds[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }

        int maxLength = 0;
        for (int i = 1; i < n - 1; i++) {
            if (lis[i] > 1 && lds[i] > 1) {
                maxLength = Math.max(maxLength, lis[i] + lds[i] - 1);
            }
        }

        return n - maxLength;
    }
}