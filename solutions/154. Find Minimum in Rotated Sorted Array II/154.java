// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

// Solution: Binary Search

// Complexity
// Time: O(LogN)
// Space: O(1)

class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int first = nums[0];
        int last = nums[n-1];
        
        if (last > first) {
            return first;
        }

        int left = 0;
        int right = n-1;

        return binarySearch(nums, left, right, first);
    }

    private int binarySearch(int[] nums, int left, int right, int first) {
        int min = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = left + ((right-left) / 2);

            min = Math.min(nums[mid], min);
            if (nums[mid] > first) {
                left = mid + 1;
            } else if ( nums[mid] < first ) {
                right = mid - 1;
            } else if (nums[mid] == first) {
                int bothSideMin = Math.min(binarySearch(nums, left, mid - 1, first), binarySearch(nums, mid + 1, right, first));
                return min = Math.min(min, bothSideMin);
            }
        }

        return min;
    }
}