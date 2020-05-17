//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性： 
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 示例 1: 
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 13
//输出: false 
// Related Topics 数组 二分查找


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int line = matrix.length;
        if (line == 0) return false;
        int col = matrix[0].length;
        int right = line * col - 1, left = 0, mid, l, c;
        while (left <= right) {
            mid = (left + right) / 2;
            l = mid / col;
            c = mid % col;
            if (matrix[l][c] == target) return true;
            if (matrix[l][c] < target) { left = mid + 1; }
            else {right = mid - 1;}
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
