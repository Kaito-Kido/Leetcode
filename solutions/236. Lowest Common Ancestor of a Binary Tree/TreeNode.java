import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    LinkedList<TreeNode> ancestorListP = new LinkedList<>();
    LinkedList<TreeNode> ancestorListQ = new LinkedList<>();

    ancestorListP = findAncestorList(root, p, ancestorListP);
    ancestorListQ = findAncestorList(root, q, ancestorListQ);

    TreeNode commonA = null;

    while (!ancestorListP.isEmpty() && !ancestorListQ.isEmpty()
        && ancestorListP.getFirst().val == ancestorListQ.getFirst().val) {
      commonA = ancestorListP.getFirst();
      ancestorListP.removeFirst();
      ancestorListQ.removeFirst();
    }

    return commonA;
  }

  private LinkedList<TreeNode> findAncestorList(TreeNode root, TreeNode value, LinkedList<TreeNode> ancestorList) {
    ancestorList.add(root);

    if (root.val == value.val) {
      return ancestorList;
    }

    if (root.left != null) {
      LinkedList<TreeNode> leftList = findAncestorList(root.left, value, ancestorList);
      if (leftList.contains(value))
        return leftList;
    }
    if (root.right != null) {
      LinkedList<TreeNode> rightList = findAncestorList(root.right, value, ancestorList);
      if (rightList.contains(value))
        return rightList;
    }
    ancestorList.removeLast();
    return ancestorList;
  }
}

// Optimize Solution
// class Solution {
// public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
// if (root == null || root == p || root == q) {
// return root;
// }

// TreeNode left = lowestCommonAncestor(root.left, p, q);
// TreeNode right = lowestCommonAncestor(root.right, p, q);

// if (left != null && right != null) {
// return root;
// }

// return left != null ? left : right;
// }
// }