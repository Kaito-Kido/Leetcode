class Solution {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        Map<Integer, ArrayList<Integer>> undirectionalRoad = new HashMap<>();
        int m = queries.length;
        int[] result = new int[m];
        int[] dp = new int[n - 1];
        Arrays.fill(dp, -1);
        for (int i = 0; i < m; i++) {
            int key = queries[i][0];
            int value = queries[i][1];
            

            if (undirectionalRoad.containsKey(key)) {
                undirectionalRoad.get(key).add(value);
            } else {
                undirectionalRoad.put(key, new ArrayList<>(Arrays.asList(value)));
            }

            result[i] = travelGraph(undirectionalRoad, n, 0, dp);

            Arrays.fill(dp, -1);
        }

        return result;
    }

    public int travelGraph(Map<Integer, ArrayList<Integer>> undirectionalRoad, int n, int currentPos, int[] dp) {
        if (currentPos >= n - 1) {
            return 0;
        }

        if (dp[currentPos] != -1) {
            return dp[currentPos];
        }

        int result = Integer.MAX_VALUE;
        if (undirectionalRoad.containsKey(currentPos)) {
            ArrayList<Integer> values = undirectionalRoad.get(currentPos);

            for (int k = 0; k < values.size(); k++) {
                result = Math.min(travelGraph(undirectionalRoad, n, values.get(k), dp) + 1, result);
            }
        }

        result = Math.min(result, travelGraph(undirectionalRoad, n, currentPos + 1, dp) + 1);
        dp[currentPos] = result;
        return result;
    }
}