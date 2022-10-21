import java.time.temporal.Temporal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static Node head = null;
    public  static Node prev = null;

    public static void main(String[] args) {

    }

    //    Q1: Convert Sorted List to Binary Search Tree
    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return helper1(head, null);
    }
    public static TreeNode helper1(ListNode start, ListNode end) {
        if (start == end) return null;
        ListNode slow = start;
        ListNode fast = start;
        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode root = new TreeNode(slow.val);
        root.left = helper1(start, slow);
        root.right = helper1(slow.next, end);
        return root;
    }

    //    Q2: Convert Binary Search Tree to Sorted Doubly Linked List
    public static Node treeToDoublyList(Node root) {
        if (head == null) return root;
        helper2(root);
        head.right.left = prev;
        prev.right = head.right;
        return head.right;
    }
    public static void helper2(Node root) {
        if(root == null) return;
        helper2(root.left);
        // first node
        if(head == null){
            head = new Node();
            head.right = root;
        } else {
            prev.right = root;
            root.left = prev;
        }
        prev = root;
        helper2(root.right);
    }

    //    Q3: Validate Binary Search Tree
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public static boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    //    Q4: Recover Binary Search Tree
    public static void recoverTree(TreeNode root) {
        if(root == null) return;
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;

        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || curr != null){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            } else{
                curr = stack.pop();
                if(prev != null && prev.val >= curr.val){
                    if(first == null) first = prev;
                    second = curr;
                }
                prev = curr;
                curr = curr.right;
            }
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    //    Q5: Binary Search Tree Iterator
        //    see BSTIterator class

    //    Q6: Serialize and Deserialize BST
        // Encodes a tree to a single string
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(sb, root);
        return sb.toString();
    }
    public static void buildString(StringBuilder sb, TreeNode root) {
        if (root == null) {
            sb.append("null").append(",");
        } else {
            sb.append(root.val).append(",");
            buildString(sb, root.left);
            buildString(sb, root.right);
        }
    }
        // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null){
            return null;
        }
        String[] strArr = data.split(",");
        Queue<String> queue = new LinkedList<>();
        Collections.addAll(queue, strArr);
        return buildTree(queue);
    }
    public static TreeNode buildTree(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String s = queue.poll();
        if (s.equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = buildTree(queue);
        root.right = buildTree(queue);

        return root;
    }

    //    Q7: Inorder Successor in BST
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;

        while(root != null){
            if(p.val < root.val){
                res = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return res;
    }

    //    Q8: Range Sum of BST
    public static int rangeSumBST(TreeNode root, int low, int high) {
        int sum = 0;
        if (root == null) return 0;
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }
        if (root.val > low) {
            sum += rangeSumBST(root.left, low, high);
        }
        if (root.val < high) {
            sum += rangeSumBST(root.right, low, high);
        }
        return sum;
    }
}
