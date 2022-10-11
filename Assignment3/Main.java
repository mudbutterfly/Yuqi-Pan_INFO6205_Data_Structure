import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public  static String[] dict = new String[]{" ", " ", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public static void main(String[] args) {
    //        Q1: Combination Sum
        int[] candidates = new int[] {2,3,6,7};
        System.out.println(combinationSum(candidates, 7));

    //        Q2: Permutations
        int[] nums = new int[] {1,2,3};
        System.out.println(permute(nums));

    //        Q3:  Letter Combinations of a Phone Number
        String digits = "23";
        System.out.println(letterCombinations(digits));

    //        Q4: Word Search
        char[][] board = new char[][] {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        String word = "ABCCED";
        System.out.println(exist(board,word));

    //        Q5: Numbers With Same Consecutive Differences
        int[] ans = numsSameConsecDiff(3,7);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }

    //        Q6: Subsets
        System.out.println(subsets(nums));

    //        Q7: Generate Parentheses
        System.out.println(generateParenthesis(3));
    //        Q8: All Paths From Source to Target
        int[][] graph = new int[][] {{1,2}, {3}, {3}, {}};
        System.out.println(allPathsSourceTarget(graph));

    }

    //        Q1: Combination Sum
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSumHelper(candidates, target, res, new ArrayList<>(), 0, 0);
        return res;
    }

    public static void combinationSumHelper(int[] candidates, int target, List<List<Integer>> res, List<Integer> curr, int start, int sum) {
        if (sum == target) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                continue;
            }
            curr.add(candidates[i]);
            sum += candidates[i];
            combinationSumHelper(candidates, target,res, curr, i, sum);
            curr.remove(curr.size()- 1);
            sum -= candidates[i];
        }
    }

    //        Q2: Permutations
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0 || nums == null) return res;
        permuteHelper(nums, res, new ArrayList<>(), new boolean[nums.length]);
        return res;
    }
    public static void permuteHelper(int[] nums, List<List<Integer>> res, List<Integer> curr, boolean[] visited) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            curr.add(nums[i]);
            visited[i] = true;
            permuteHelper(nums, res, curr, visited);
            curr.remove(curr.size() - 1);
            visited[i] = false;
        }
    }

    //        Q3: Letter Combinations of a Phone Number
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        letterCombinationsDfs(digits, 0, new StringBuilder(), res);
        return res;
    }
    public static void letterCombinationsDfs(String digits, int index, StringBuilder sb, List<String> res) {
        if (index == digits.length()){
            res.add(sb.toString());
            return;
        }
        int num = digits.charAt(index) - '0';
        String str = dict[num];

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            sb.append(c);
            letterCombinationsDfs(digits,index + 1,sb,res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    //    Q4: Word Search
    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(existHelper(board,word,i,j,0)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean existHelper(char[][] board, String word, int i, int j, int idx) {
        if (idx == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || word.charAt(idx) != board[i][j]) {
            return false;
        }
        char temp = board[i][j];
        board[i][j] = '*';
        boolean found = existHelper(board,word,i + 1,j,idx+1) ||
                existHelper(board,word,i,j + 1,idx+1) ||
                existHelper(board,word,i - 1,j,idx+1) ||
                existHelper(board,word,i,j - 1,idx+1);
        board[i][j] = temp;
        return found;
    }

    //    Q5: Numbers With Same Consecutive Differences
    public static int[] numsSameConsecDiff(int n, int k) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            consecDiffHelper(res, i, 1, n, k);
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
    public static void consecDiffHelper(List<Integer> res, int curItem, int idx, int n, int k) {
        if (idx == n) {
            res.add(curItem);
            return;
        }
        int a = curItem % 10;
        if (k == 0) {
            int temp = (curItem * 10) + a;
            consecDiffHelper(res,temp, idx + 1, n, k);
        } else {
            if (a + k <= 9) {
                int temp = (curItem*10) + a + k;
                consecDiffHelper(res,temp, idx + 1, n, k);
            }
            if (a - k >= 0) {
                int temp = (curItem * 10) + a - k;
                consecDiffHelper(res,temp, idx + 1, n, k);
            }
        }
    }

    //        Q6: Subsets
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 1) return res;
        subsetsHelper(res, nums, new ArrayList<>(), 0);
        return res;
    }
    public static void subsetsHelper(List<List<Integer>> res, int[] nums, List<Integer> curr, int idx) {
        if (idx == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }
        curr.add(nums[idx]);
        subsetsHelper(res, nums,curr,idx + 1);
        curr.remove(curr.size()- 1);
        subsetsHelper(res, nums,curr,idx + 1);
    }

    //    Q7: Generate Parentheses
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        parenthesisHelper(res, "", 0, 0, n);
        return res;
    }
    public static void parenthesisHelper(List<String> res, String curr, int open, int close, int n) {
        if (2 * n == curr.length()) {
            res.add(curr);
            return;
        }
        if (open < n) {
            parenthesisHelper(res, curr + "(", open + 1, close, n);
        }
        if (close < open) {
            parenthesisHelper(res, curr + ")", open, close + 1, n);
        }
    }

    //        Q8: All Paths From Source to Target
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        if (graph.length == 0) return res;
        List<Integer> path = new ArrayList<>();
        path.add(0);
        pathDfs(graph,res,path,0);
        return res;
    }
    public static void pathDfs(int[][] graph, List<List<Integer>> res, List<Integer> path, int start) {
        if (start == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i : graph[start]) {
            path.add(i);
            pathDfs(graph,res,path,i);
            path.remove(path.size() -1);
        }
    }




}
