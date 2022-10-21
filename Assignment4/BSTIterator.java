import java.util.Stack;

public class BSTIterator {
    private TreeNode curr;
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        curr = root;
        stack = new Stack<>();
    }

    public int next() {
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
        curr = stack.pop();
        int val = curr.val;
        //move to right(if is null, will continue to pop item in stack)
        curr = curr.right;
        return val;
    }

    public boolean hasNext() {
        if (!stack.isEmpty() || curr != null) {
            return true;
        } else {
            return false;
        }
    }
}
