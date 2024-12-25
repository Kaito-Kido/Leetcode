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
        int currentVal = root.val;

        while (true) {
            if (currentVal < p.val && currentVal < q.val) {
                root = root.right;
                currentVal = root.val;
                continue;
            } else if (currentVal > p.val && currentVal > q.val) {
                root = root.left;
                currentVal = root.val;
                continue;
            } else {
                break;
            }
        }

        return root;
    }
}