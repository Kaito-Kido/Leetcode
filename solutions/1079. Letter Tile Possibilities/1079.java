class Solution {
  public int numTilePossibilities(String tiles) {
    char[] tileArray = tiles.toCharArray();
    Arrays.sort(tileArray); // Sắp xếp để xử lý trùng lặp
    boolean[] visited = new boolean[tiles.length()];
    return backtracking(tileArray, visited);
  }

  private int backtracking(char[] tiles, boolean[] visited) {
    int count = 0;
    for (int i = 0; i < tiles.length; i++) {
      if (visited[i])
        continue;
      // Bỏ qua phần tử trùng lặp nếu phần tử trước đó chưa được chọn
      if (i > 0 && tiles[i] == tiles[i - 1] && !visited[i - 1])
        continue;

      visited[i] = true;
      count += 1 + backtracking(tiles, visited); // Đếm chính nó và tất cả nhánh con
      visited[i] = false;
    }
    return count;
  }
}