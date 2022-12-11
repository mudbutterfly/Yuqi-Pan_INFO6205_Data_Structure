import java.util.*;

public class Main {
    public static void main(String[] args) {
    }

    public static int[][] dir = {{0,1},{0,-1},{-1,0},{1,0}};

    //    Q1: group anagram
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0 || strs == null) return new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String keyStr = String.valueOf(c);
            if (!map.containsKey(keyStr)) {
                map.put(keyStr, new ArrayList<>());
            }
            map.get(keyStr).add(s);
        }

        return new ArrayList<>(map.values());
    }
    //    time complexity: O(N)


    //    Q2: number of islands
    public static int numOfIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    helper(grid, i, j);
                }
            }
        }
        return count;
    }
    public static void helper(char[][] grid, int i ,int j) {
        if (i < 0 || j < 0 || i >= grid.length|| j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '*';
        for (int k = 0; k < 4; k++) {
            int x = i + dir[k][0];
            int y = j + dir[k][1];
            helper(grid,x,y);
        }
    }
    //    time complexity: O(N)


    //Q3: remove parentheses
    public static String removeParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isAlphabetic(ch)) {
                continue;
            }
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty() && s.charAt(stack.peek())=='(') {
                    //can match to get a pair
                    stack.pop();
                } else {
                    //get the index of the char that need to be delte
                    stack.push(i);
                }
            }
        }

        StringBuilder res = new StringBuilder();
        HashSet<Integer> set = new HashSet<>(stack);
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(i)) {
                //if set contains(i), means need to delete
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }
    //    time complexity: O(N)


    //Q4:unique path
    public static int uniquePath(int[][] grid) {
        int[] dp = new int[grid.length];
        dp[0] = 1;
        for (int j = 0; j < grid[0].length; j++) {
            //get col
            for (int i = 0; i < grid.length; i++) {
                //get row
                if (grid[i][j] == 1) {
                    dp[i] = 0;
                } else if (i > 0) {
                    dp[i] += dp[i-1];
                }
            }
        }
        return dp[grid.length - 1];
    }
    //    time complexity: O(N^2)

}
