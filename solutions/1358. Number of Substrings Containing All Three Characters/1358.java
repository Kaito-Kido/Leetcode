import java.util.LinkedList;

class Solution {
  public int numberOfSubstrings(String s) {
    LinkedList<Integer> linkA = new LinkedList<>();
    LinkedList<Integer> linkB = new LinkedList<>();
    LinkedList<Integer> linkC = new LinkedList<>();
    int count = 0;
    int n = s.length();

    // Lưu chỉ số của các ký tự 'a', 'b', 'c' vào các danh sách tương ứng
    for (int i = 0; i < n; i++) {
      if (s.charAt(i) == 'a') {
        linkA.add(i);
      } else if (s.charAt(i) == 'b') {
        linkB.add(i);
      } else {
        linkC.add(i);
      }
    }

    for (int i = 0; i < n; i++) {
      if (!linkA.isEmpty() && !linkB.isEmpty() && !linkC.isEmpty()) {
        count += n - Math.max(Math.max(linkA.getFirst(), linkB.getFirst()), linkC.getFirst());
      } else {
        break;
      }

      if (s.charAt(i) == 'a' && !linkA.isEmpty()) {
        linkA.removeFirst();
      } else if (s.charAt(i) == 'b' && !linkB.isEmpty()) {
        linkB.removeFirst();
      } else if (s.charAt(i) == 'c' && !linkC.isEmpty()) {
        linkC.removeFirst();
      }
    }

    return count;
  }
}
