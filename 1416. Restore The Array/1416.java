// https://leetcode.com/problems/restore-the-array/

// Solution: Precomputation

// Complexity
// Time: O(n)
// Space: O(n)

class Solution {
    public int numberOfArrays(String s, int k) {
        final int MOD = 1000000007;
        int n = s.length();
        int left = 0;
        int right = 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int total = 0;

        while ( right <= n ) {
            if (s.charAt(right - 1) != '0' && right != 1) {
                total = (dp[right - 1] * 2) % MOD;
            } else {
                total = dp[right - 1];
            }

            while (left < n && s.charAt(left) == '0' && left < right) {
                left++;
            }

            int sub = 0;

            while (left < right && Long.parseLong(s.substring(left, right)) > k) {
                sub = (sub + dp[left]) % MOD;
                left++;
            }

            dp[right] = (total - sub + MOD) % MOD;
            right++;
        }

        return dp[n];
    }
}