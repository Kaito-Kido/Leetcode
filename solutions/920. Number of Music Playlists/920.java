// https://leetcode.com/problems/number-of-music-playlists
//
// Solution: backtracking + memoization
//
// Complexity:
// Time: O(n^3 * k)
// Space: O(n^3)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    final int MOD = 1000000007;

    public int numMusicPlaylists(int n, int goal, int k) {
        // Memoization map
        Map<String, Integer> memo = new HashMap<>();

        // Start the recursion with an empty playlist
        return recursion(n, goal, k, 0, new ArrayList<>(), memo);
    }

    private int recursion(int n, int goal, int k, int index, List<Integer> queue, Map<String, Integer> memo) {
        Set<Integer> visited = new HashSet<>(queue);

        if (index == goal) {
            return visited.size() == n ? 1 : 0;
        }

        if (n - visited.size() > goal - index) {
            return 0;
        }

        // Create a unique key for memoization
        String key = index + "-" + visited.size();
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            boolean found = false;
            for (int j = Math.max(queue.size() - k, 0); j < queue.size(); j++) {
                if (queue.get(j) == i) {
                    found = true;
                    break;
                }
            }
            if (found)
                continue;

            queue.add(i);
            result = (result + recursion(n, goal, k, index + 1, queue, memo)) % MOD;
            queue.remove(queue.size() - 1);
        }

        memo.put(key, result);
        return result;
    }
}