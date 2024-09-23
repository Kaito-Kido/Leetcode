// https://leetcode.com/problems/create-maximum-number/
// Complexity
// Time: O(k * (m + n + k))
// Space: O(k + m + n)

class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];
        Arrays.fill(result, 0);
        for (int i = Math.max(0, k - nums2.length); i <= Math.min(k, nums1.length); i++) {
            int[] sub1 = maxSubsequence(nums1, i);
            int[] sub2 = maxSubsequence(nums2, k - i);
            int[] newResult = merge(sub1, sub2);

            if (greater(newResult, result)) {
                result = newResult;
            } 
        }

        return result;
    }

    private int[] maxSubsequence(int[] nums, int size) {
        if (size == 0) return new int[]{};
       int[] result = new int[size];
       int top = 0;
       int remain = size;

       for (int i = 0; i < nums.length; i++) {
        while (top > 0 && nums[i] > result[top-1] && remain <= nums.length - i - 1) {
            remain++;
            top--;
        }

        if (top < size) {
            result[top] = nums[i];
            top++;
            remain--;
        }
       }

       return result;       
    }

    private int[] merge(int[] subsequence1, int[] subsequence2) {
        int[] result = new int[subsequence1.length + subsequence2.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < subsequence1.length && j < subsequence2.length) {
            if (subsequence1[i] > subsequence2[j]) {
                result[k] = subsequence1[i];
                i++;
            } else if (subsequence2[j] > subsequence1[i] ){
                result[k] = subsequence2[j];
                j++;
            } else {
                if (greater(Arrays.copyOfRange(subsequence1, i, subsequence1.length), Arrays.copyOfRange(subsequence2, j, subsequence2.length))) {
                    result[k] = subsequence1[i];
                    i++;
                } else {
                    result[k] = subsequence2[j];
                    j++;
                }
            }
            k++;
        }

        while (i < subsequence1.length) {
            result[k] = subsequence1[i];
            i++;
            k++;
        }

        while (j < subsequence2.length) {
            result[k] = subsequence2[j];
            j++;
            k++;
        }
    
        return result;
    }

    private boolean greater(int[] nums1, int[] nums2) {
        int n = Math.min(nums1.length, nums2.length);
        for (int i = 0; i < n; i++) {
            if (nums1[i] > nums2[i]) {
                return true;
            } else if ( nums1[i] < nums2[i]) return false;
        }

        return nums1.length > nums2.length;
    }
}