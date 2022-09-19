import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[]args){
//                Q1     expected output: 4,5,1,2,3
        ListNode node4 = new ListNode(5);
        ListNode node3 = new ListNode(4,node4);
        ListNode node2 = new ListNode(3,node3);
        ListNode node1 = new ListNode(2,node2);
        ListNode node0 = new ListNode(1,node1);
        ListNode res = rotateRight(node0, 2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }

        //        Q2     expected output: 1,2,3,4,5
        ListNode node66 = new ListNode(6);
        ListNode node55 = new ListNode(5,node66);
        ListNode node44 = new ListNode(4,node55);
        ListNode node33 = new ListNode(3,node44);
        ListNode node22 = new ListNode(6,node33);
        ListNode node11 = new ListNode(2,node22);
        ListNode node00 = new ListNode(1,node11);
        ListNode res2 = removeElements(node00, 6);
        while (res2 != null) {
            System.out.println(res2.val);
            res2 = res2.next;
        }

        //        Q3     expected output: 1,4,3,2,5
        ListNode res3 = swapNodes(node0, 2);
        while (res3 != null) {
            System.out.println(res3.val);
            res3 = res3.next;
        }

        //        Q5     expected output: 3,4,1,2
        Node n1 = new Node(3);
        Node n3 = new Node(1,n1);
        Node n2 = new Node(4,n3);
        n1.next = n2;
        Node res5 = insert(n1, 2);
        for (int i = 0; i < 4; i++) {
            System.out.println(res5.val);
            res5 = res5.next;
        }

    }

    //  Question1 Rotate List
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode index = head;
        int len = 1;
        while (index.next != null) {
            len++;
            index = index.next;
        }
        index.next = head;
        //find old list end
        for (int i = 1; i < len - k%len; i++) {
            head = head.next;
        }
        //find new list start(let end point to null, disconnect)
        ListNode res = head.next;
        head.next = null;
        return res;
    }

    //  Question2 Rotate List
    public static ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode n = head;
        while (n != null && n.next != null) {
            if (n.next.val == val) {
                //find val, skip the current node
                n.next = n.next.next;
            } else {
                n = n.next;
            }
        }
        return head;
    }

    //  Question3 Swapping Nodes in a Linked List
    public static ListNode swapNodes(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode n1 = head, n2 = head;

        for (int i = 0; i < k - 1; i++) {
            //move fast k-1 position ahead;
            fast = fast.next;
        }
        //the node that is k away from the head
        n1 = fast;

        while (fast.next != null) {
            //move fast to end of the list
            fast = fast.next;
            //move slow to k position away from end;
            slow = slow.next;
        }
        n2 = slow;
        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
        return head;
    }

    //  Question4 Split Linked List in Parts
    public static ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] res = new ListNode[k];
        int len = 0;
        ListNode index = head;
        while (index != null) {
            index = index.next;
            len++;
        }
        int remain = len%k;
        int size = len/k;
        for (int i = 0; i < k && head != null; i++) {
            res[i] = head;
            ListNode prev = null;
            int currSize = size;
            //if remain is greater than 1, put more into current
            if (remain-- > 0) currSize++;
            for (int j = 0; j < currSize; j++) {
                //record the last node of the current split
                prev = head;
                head = head.next;
            }
            prev.next = null;
        }
        return res;
    }

    //  Question5 Insert into a Sorted Circular Linked List
    public static Node insert(Node head, int insertVal) {
        Node n = new Node(insertVal);
        if (head == null) {
            n.next = n;
            return n;
        }
        Node node = head;
        while (node.next != head) {
            if (node.val <= node.next.val) {
                //increasing list
                if (node.val <= n.val && n.val <= node.next.val) {
                    break;
                }
            } else if (node.val < n.val || n.val < node.next.val) {
                //decreasing list but find a point that sudden increase(end)-> insert point
                break;
            }
            node = node.next;
        }
        n.next = node.next;
        node.next = n;
        return head;
    }

}

