// https://leetcode.com/problems/minimum-incompatibility/
//
// Solution:
// Using bitmask and backtracking to calculate minimum incompatibility of every possible subsets
// 
// Complexity:
// Time: O(N∗2^N)
// Space: O(2^N)

class Solution {
    public int minimumIncompatibility(int[] nums, int k) {
        final int subsetSize = nums.length/k;
        final int n = nums.length;
        final int maxMask = (1 << n);

        int[] mem = new int[1 << n];
        Arrays.fill(mem, -1);
        Arrays.sort(nums);
        
        int min = backtracking(nums, k, maxMask - 1, mem, subsetSize);

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int backtracking(int[] nums, int k, int mask, int[] mem, int subsetSize) {
        if (mem[mask] != -1) return mem[mask];

        if (Integer.bitCount(mask) == subsetSize && isUnique(nums, mask, subsetSize)) {
            mem[mask] = getImcompatibility(nums, mask);
            return mem[mask];
        }

        int result = Integer.MAX_VALUE;
        for (int submask = mask; submask > 0; submask = (submask - 1) & mask) {
            if (Integer.bitCount(submask) != subsetSize || !isUnique(nums, submask, subsetSize) ) continue;
                
            int left = backtracking(nums, k, mask & ~submask, mem, subsetSize);

            if (left != Integer.MAX_VALUE) result = Math.min(result, getImcompatibility(nums, submask) + left);
        }

        mem[mask] = result;
        return result;
    }

    private boolean isUnique(int[] nums, int mask, int subsetSize) {
        int used = 0;

        for (int i = 0; i < nums.length; i++) {
            if (((mask >> i) & 1) == 1) {
                used |= (1 << nums[i]);
            };    
        }

        return Integer.bitCount(used) == subsetSize;
    }

    private int getImcompatibility(int[] nums, int mask) {
        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (((mask >> i) & 1) == 1) {
                max = Math.max(max, nums[i]);
                min = Math.min(min, nums[i]);
            }
        }

        return max - min;
    }
}