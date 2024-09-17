// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
// Complexity
// Time: O(n)
// Space: O(n)

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] leftProfit = new int[n];
        int[] rightProfit = new int[n];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int currentMaxProfitLeft = 0;
        int currentMaxProfitRight = 0;
        for (int i = 0; i < n; i++) {
            if (min == Integer.MAX_VALUE) {
                leftProfit[i] = 0;
                min = prices[i];
            } else {
                currentMaxProfitLeft = Math.max(Math.max(prices[i] - min, 0), currentMaxProfitLeft);
                leftProfit[i] = currentMaxProfitLeft;
                min = Math.min(min, prices[i]);
            }

            int rightIndex = n - i - 1;

            if (max == Integer.MIN_VALUE) {
                rightProfit[rightIndex] = 0;
                max = prices[rightIndex];
            } else {
                currentMaxProfitRight = Math.max( Math.max(max - prices[rightIndex], 0), currentMaxProfitRight);
                rightProfit[rightIndex] = currentMaxProfitRight;
                max = Math.max(max, prices[rightIndex]);
            }
        }



        int currentMaxProfit = 0;

        for (int i = 0; i < n - 1; i++) {
            currentMaxProfit = Math.max(currentMaxProfit, leftProfit[i] + rightProfit[i]);
        }

        return currentMaxProfit;
    }
}