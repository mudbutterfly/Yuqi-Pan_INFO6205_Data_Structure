public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
    public Node parent;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next, Node _parent) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
        parent = _parent;
    }
}
