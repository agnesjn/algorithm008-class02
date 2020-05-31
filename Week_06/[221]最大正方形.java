//在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。 
//
// 示例: 
//
// 输入: 
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//输出: 4 
// Related Topics 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximalSquare(char[][] matrix) {
        int line = matrix.length;
        if (line == 0) return 0;
        int col = matrix[0].length;
        boolean[][] mat = new boolean[line][col];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < col; j++) {
                mat[i][j] = getNum(matrix[i][j]);
            }
        }
        int[][] dp = new int[line][col];
        int area = 0;
        for (int i = 0; i < line; i++) {
            dp[i][0] = mat[i][0]? 1 : 0;
            area = Math.max(area, dp[i][0]);
        }
        for (int i = 0; i < col; i++) {
            dp[0][i] = mat[0][i]? 1 : 0;
            area = Math.max(area, dp[0][i]);
        }
        for (int i = 1; i < line; i++) {
            for (int j = 1; j < col; j++) {
                if (mat[i][j]) dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                else dp[i][j] = mat[i][j] ? 1 : 0;
                area = Math.max(area, dp[i][j]);
            }
        }

        return area * area;
    }

    public boolean getNum(char a) {
        return (int)a - 48 > 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
