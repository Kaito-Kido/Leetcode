// Complexity
// Time: O(N)
// Space: O(N + 27)

class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] chars = new int['z' - 'a' + 1];

        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
        }        

        int currentIndex = chars.length - 1;
        int smallerIndex = chars.length -1;
        int count = 1;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            while(currentIndex >= 0 && chars[currentIndex] == 0) {
                currentIndex--;
                count = 1;
            }

            if (currentIndex < 0) {
                break;
            }

            while (smallerIndex >= 0 && (smallerIndex >= currentIndex || chars[smallerIndex] == 0)) {
                smallerIndex--;
            }

            if (count > repeatLimit) {
                if (smallerIndex < 0) break;
                result.append((char) ('a' + smallerIndex));
                chars[smallerIndex]--;
                count = 1;
            } else {
                result.append((char) ('a' + currentIndex));
                chars[currentIndex]--;
                count++;
            }
        }

        return result.toString();
    }

}