// https://leetcode.com/problems/number-of-atoms/

// Solution: Backtracking
// Complexity
// Time: O(n)


class Solution {
    public String countOfAtoms(String formula) {
        Map<String, Integer> map = new HashMap<>();
        int n = formula.length();
        map = backtracking(formula, 0, n).getMap();
        Map<String, Integer> sortedMap = new TreeMap<>(map);

        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            result.append(entry.getKey());
            if (entry.getValue() > 1) {
                result.append(entry.getValue());
            }
        }

        return result.toString();
    }

    public Result backtracking(String formula, int index, int n) {
        Map<String, Integer> localMap = new HashMap<>();
        while (index < n) {
            if (index < n && formula.charAt(index) == '(') {
                Result result = backtracking(formula, index + 1, n);
                index = result.getIndex();

                int multiplier = 1;
                if (index < n && Character.isDigit(formula.charAt(index))) {
                    int start = index;
                    while (index < n && Character.isDigit(formula.charAt(index))) {
                        index++;
                    }
                    multiplier = Integer.parseInt(formula.substring(start, index));
                }

                for (Map.Entry<String, Integer> entry : result.getMap().entrySet()) {
                    localMap.put(entry.getKey(), localMap.getOrDefault(entry.getKey(), 0) + entry.getValue() * multiplier);
                }
            } else if (index < n && formula.charAt(index) == ')') {
                return new Result(localMap, index + 1);
            } else {
                StringBuilder currentElement = new StringBuilder();
                currentElement.append(formula.charAt(index++));
                while (index < n && Character.isLetter(formula.charAt(index)) && Character.isLowerCase(formula.charAt(index))) {
                    currentElement.append(formula.charAt(index++));
                }

                int count = 1;
                if (index < n && Character.isDigit(formula.charAt(index))) {
                    int start = index;
                    while (index < n && Character.isDigit(formula.charAt(index))) {
                        index++;
                    }
                    count = Integer.parseInt(formula.substring(start, index));
                }

                localMap.put(currentElement.toString(), localMap.getOrDefault(currentElement.toString(), 0) + count);
            }
        }

        return new Result(localMap, index + 1);
    }

    class Result {
        private final Map<String, Integer> map;
        private final int index;

        public Result(Map<String, Integer> map, int index) {
            this.map = map;
            this.index = index;
        }

        public Map<String, Integer> getMap() {
            return this.map;
        }

        public int getIndex() {
            return this.index;
        } 
    }
}