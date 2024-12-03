class Solution {
    public String addSpaces(String s, int[] spaces) {
        int spaceIndex = 0;
        int spaceLen = spaces.length;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (spaceIndex < spaceLen && spaces[spaceIndex] == i) {
                result.append(" ");
                spaceIndex++;
            }

            result.append(s.charAt(i));
        }

        return result.toString();

    }
}