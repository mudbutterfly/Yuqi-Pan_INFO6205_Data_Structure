import javafx.util.Pair;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static int max = 0;
    public static int min = 0;

    public static void main(String[] args) {
        //        Q1: Symmetric Tree
        TreeNode node6 = new TreeNode(3,null,null);
        TreeNode node5 = new TreeNode(4,null,null);
        TreeNode node4 = new TreeNode(4,null,null);
        TreeNode node3 = new TreeNode(3,null,null);
        TreeNode node2 = new TreeNode(2,node5,node6);
        TreeNode node1 = new TreeNode(2,node3,node4);
        TreeNode root = new TreeNode(1,node1,node2);
        System.out.println(isSymmetric(root));

        //       Q2: Maximum Depth of Binary Tree
        System.out.println(maxDepth(root));

        //      Q3: Binary Tree Right Side View
        System.out.println(rightSideView(root));

        //      Q4: Binary Tree Zigzag Level Order Traversal
        System.out.println(zigzagLevelOrder(root));

        //     Q5: Binary Tree Vertical Order Traversal
        System.out.println(verticalOrder(root));
        //     Q6: Maximum Width of Binary Tree
        System.out.println(widthOfBinaryTree(root));
        //     Q7: Lowest Common Ancestor of a Binary Tree
        //     Q8: Find Leaves of Binary Tree
        System.out.println(findLeaves(root));

    }


    //        Q1: Symmetric Tree
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);
        while (!q.isEmpty()) {
            TreeNode left = q.poll();
            TreeNode right = q.poll();
            if (left == null && right == null) continue;
            if (left == null || right == null || left.val != right.val) return false;
            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);
        }
        return true;
    }

    //       Q2: Maximum Depth of Binary Tree
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        //recursion, always get current maxdepth
        return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }

    //      Q3: Binary Tree Right Side View
    public static List<Integer> rightSideView(TreeNode root){
        List<Integer> res = new ArrayList<>();
        rightView(root, res, 0);
        return res;
    }
    public static void rightView(TreeNode root, List<Integer> res, int currDepth) {
        if (root == null) return;
        if (currDepth == res.size()) res.add(root.val);
        //get the rightest as far as possible
        rightView(root.right, res, currDepth + 1);
        //back to check left, and see if current level aleady has a righest node
        rightView(root.left, res, currDepth + 1);
    }

    //      Q4: Binary Tree Zigzag Level Order Traversal
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);

        boolean left2right = true;

        while (!que.isEmpty()) {
            int size = que.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode curr = que.poll();
                if (left2right == true) {
                    level.add(curr.val);
                } else {
                    level.add(0, curr.val);
                }
                if (curr.left != null) que.add(curr.left);
                if (curr.right != null) que.add(curr.right);
            }

            res.add(level);
            left2right = !left2right;
        }

        return res;
    }

    //    Q5: Binary Tree Vertical Order Traversal
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return  res;
        helper(root, 0);
        for (int i = min; i <= max; i++) {
            //get the total number of columns needed
            res.add(new ArrayList<>());
        }

        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> idx = new LinkedList<>();

        q.add(root);
        idx.add(-min);

        while (!q.isEmpty()) {
            TreeNode currNode = q.poll();
            int currIdx = idx.poll();
            res.get(currIdx).add(currNode.val);
            if (currNode.left != null) {
                q.add(currNode.left);
                idx.add(currIdx - 1);
            }
            if (currNode.right != null) {
                q.add(currNode.right);
                idx.add(currIdx + 1);
            }
        }
        return res;
    }

    private static void helper(TreeNode root, int index) {
        if(root == null) return;
        min = Math.min(min, index);
        max = Math.max(max, index);
        helper(root.left, index - 1);
        helper(root.right, index + 1);
    }

    //     Q6: Maximum Width of Binary Tree
    public static int widthOfBinaryTree(TreeNode root) {
        //idx for every node is its root node's idx *2(for left child node)and idx *2+1 (for right child node)
        if (root == null) return 0;
        Queue<Pair<TreeNode,Integer>> q = new LinkedList<>();
        int maxWidth = 0;
        q.offer(new Pair<>(root,0));
        while (!q.isEmpty()) {
            int size = q.size();
            int start = -1, end = -1;
            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> element = q.poll();
                TreeNode node = element.getKey();
                int idx = element.getValue();

                if (i == 0) {
                    //find the start of the current row
                    start = idx;
                }
                //otherwise is the current end of the current row
                end = idx;

                if (node.left != null) {
                    q.offer(new Pair<>(node.left, 2 * idx));
                }
                if (node.right != null) {
                    q.offer(new Pair<>(node.right, 2 * idx + 1));
                }
                maxWidth = Math.max(maxWidth, end - start + 1);
            }
        }
        return maxWidth;
    }

    //     Q7: Lowest Common Ancestor of a Binary Tree
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if(root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;
        if (left != null) {
            return left;
        } else {
            return right;
        }
    }

    //    Q8: Find Leaves of Binary Tree

    public static List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        while (root != null) {
            List<Integer> temp = new ArrayList<>();
            root = deleteLeaves(root, temp);
            res.add(temp);
        }
        return res;
    }
    private static TreeNode deleteLeaves(TreeNode root, List<Integer> temp){
        if (root == null) return null;
        if (root.left == null && root.right == null) {
            //find the leaf
            temp.add(root.val);
            //set the current leaf to null(delete)
            return null;
        }
        //assign new left and right to node
        root.left = deleteLeaves(root.left, temp);
        root.right = deleteLeaves(root.right, temp);
        return root;
    }

}
