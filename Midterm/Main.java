import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

    }

    //Q1:
    public Node connect(Node root) {
        if (root == null) return root;
        Node curr = root;
        while (curr != null) {
            Node head = curr;
            while (curr != null) {
                if (curr.right != null) {
                    curr.right.next = curr.left;
                }
                if (curr.left != null && curr.next != null) {
                    curr.left.next = curr.next.right;
                }
                curr = curr.next;
            }
            curr = head.right;
        }
        return root;
    }
    //timeComplexity: O(N)

    //Q2:
    public Node returnParent(Node root, Node parent) {
        if (root == null) return root;
        root.parent = parent;
        if (root.left != null) {
            returnParent(root.left, root);
        }
        if (root.right != null) {
            returnParent(root.right, root);
        }
        return root;
    }
    //timeComplexity: O(N)


    //Q3:
    public ArrayList<Integer> inRangeNums(Node root, int a, int b) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int len = que.size();
            for (int i = 0; i < len; i++) {
                Node curr = que.poll();
                if (curr.val >= a && curr.val <= b) {
                    res.add(curr.val);
                }
                if (curr.left != null) {
                    que.offer(curr.left);
                }
                if (curr.right != null) {
                    que.offer(curr.right);
                }
            }
        }
        return res;
    }
    //timeComplexity: O(N)


    //Q4:
    public Node sortedInsert(Node head, int insertVal) {
        Node n = new Node(insertVal);
        if (head == null) {
            n.next = n;
            return n;
        }

        Node node = head;
        while (node.next != null) {
            if (node.val <= n.val && n.val <= node.next.val ) {
                break;
            }
            node = node.next;
        }
        n.next = node.next;
        node.next = n;
        return head;
    }
    //timeComplexity: O(N)

    //Q5:
    public int[] indexArray(int[] nums, int[] values) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) continue;
            map.put(nums[i], i);
        }
        int[] res = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            res[i] = map.getOrDefault(values[i],-1);
        }
        return res;
    }
    //timeComplexity: O(N)

}