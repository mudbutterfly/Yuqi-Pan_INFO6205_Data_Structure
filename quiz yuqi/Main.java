import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        //    question 1: rotate the array
        int[] nums = new int[] {1,2,3,4,5,6,7};
        int k = 3;
        nums = rotate(nums, k);
        for (int n : nums) {
            System.out.println(n);
        }

        //    question2: same or not same root
        TreeNode left1 = new TreeNode(2, null, null);
        TreeNode right1 = new TreeNode(3, null, null);
        TreeNode n1 = new TreeNode(1,left1,right1);
        TreeNode left2 = new TreeNode(2, null, null);
        TreeNode right2 = new TreeNode(3, null, null);
        TreeNode n2 = new TreeNode(1,left2,right2);
        System.out.println(isSameTree(n1,n2));
        //    question3: Binary Tree Level order traversal
    }

    //    question 1: rotate the array
    public static int[] rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        return nums;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    //    question2: same or not same root
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        } else if (isSameTree(p.left, q.left) && isSameTree(p.right, q.right)) {
            return p.val == q.val;
        } else {
            return false;
        }
    }

    //    question3: Binary Tree Level order traversal
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);

        while (!que.isEmpty()) {
            int len = que.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                TreeNode curr = que.poll();
                level.add(curr.val);
                if (curr.left != null) {
                    que.offer(curr.left);
                }
                if (curr.right != null) {
                    que.offer(curr.right);
                }
            }
            res.add(level);
        }
        return res;
    }
}