// https://leetcode.com/problems/palindrome-pairs/

// Solution: Same solution with 2 sum problem, The different is how we find the pair, split the word in 2 part, if one part is palindrome, we find the reverse of other part in hash

// Complexity:
// Time: O(MxN)
// Space: O(n)

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        List<List<Integer>> pairs = new ArrayList<>();
        
        // Store each word in reverse in the map
        for (int i = 0; i < words.length; i++) {
            map.put(new StringBuilder(words[i]).reverse().toString(), i);
        }

        // Iterate over each word and look for palindrome pairs
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int n = word.length();
            
            // Check every possible split point
            for (int j = 0; j <= n; j++) {
                String leftPart = word.substring(0, j);
                String rightPart = word.substring(j, n);

                // If left part is a palindrome, check if reversed right part exists in the map
                if (isPalindrome(leftPart) && map.containsKey(rightPart) && map.get(rightPart) != i) {
                    pairs.add(new ArrayList<>(Arrays.asList(map.get(rightPart), i)));
                }

                // If right part is a palindrome, check if reversed left part exists in the map
                // We also check j != n to avoid adding the same pair twice when leftPart is empty
                if (isPalindrome(rightPart) && map.containsKey(leftPart) && map.get(leftPart) != i && j != n) {
                    pairs.add(new ArrayList<>(Arrays.asList(i, map.get(leftPart))));
                }
            }
        }

        return pairs;
    }

    private boolean isPalindrome(String word) {
        int start = 0, end = word.length() - 1;
        while (start < end) {
            if (word.charAt(start++) != word.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}
