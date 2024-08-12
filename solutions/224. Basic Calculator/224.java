// https://leetcode.com/problems/basic-calculator/
//
// Solution: backtracking
//
// Complexity:
// Time: O(n)
// Space: O(n)

class Solution {
    public int calculate(String s) {
        return subCalculate(s, 0).value();
    }

    public Pair<Integer, Integer> subCalculate(String s, int index) {
        int result = 0;
        String operator = "";

        for (int i = index; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if (Character.isDigit(currentChar)) {
                Pair<Integer, Integer> p = currentNum(s, i);

                if ("plus".equals(operator) || operator.isEmpty()) {
                    result += p.value();
                    i = p.key();
                } else if ("minus".equals(operator)) {
                    result -= p.value();
                    i = p.key();
                }
            } else if (currentChar == '(') {
                if ("plus".equals(operator) || operator.isEmpty()) {
                    Pair<Integer, Integer> p = subCalculate(s, i + 1);
                    result += p.value();
                    i = p.key();
                } else if ("minus".equals(operator)) {
                    Pair<Integer, Integer> p = subCalculate(s, i + 1);
                    result -= p.value();
                    i = p.key();
                }
            } else if (currentChar == ')') {
                return new Pair<>(i, result);
            } else if (currentChar == '+') {
                operator = "plus";
            } else if (currentChar == '-') {
                operator = "minus";
            }
        }

        return new Pair<>(index, result);
    }

    public Pair currentNum(String s, int index) {
        int end = index;

        while (end < s.length() && Character.isDigit(s.charAt(end))) {
            end++;
        }

        return new Pair(end - 1, Integer.valueOf(s.substring(index, end)));
    }

    // Correctly define the Pair record with generic type variables
    public record Pair<K, V>(K key, V value) {
    }
}
