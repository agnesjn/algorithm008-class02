//一条包含字母 A-Z 的消息通过以下方式进行了编码： 
//
// 'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 示例 1: 
//
// 输入: "12"
//输出: 2
//解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2: 
//
// 输入: "226"
//输出: 3
//解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
// Related Topics 字符串 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numDecodings(String s) {
        int len = s.length();
        if (len == 0) return 0;
        int aNum = getNum(s, 0);
        if (aNum == 0) return 0;
        if (len == 1) return 1;
        int a = 1, b, temp, tempNum, bNum = getNum(s,1);
        if (bNum == 0) {
            if (aNum > 2) return 0;
            b = 1;
        }
        else if (isAlpha(aNum, bNum)) b = 2;
        else b = 1;
        for (int i = 2; i < len; i++) {
            tempNum = getNum(s,i);
            if (isAlpha(bNum, tempNum)) {
                if (tempNum == 0) temp = a;
                else temp = a + b;
            } else {
                if (tempNum == 0) return 0;
                else temp = b;
            }
            bNum = tempNum;
            a = b;
            b = temp;
        }
        return b;
    }

    public int getNum(String s, int p) {
        return (int)s.charAt(p)-48;
    }

    public boolean isAlpha(int a, int b) {
        if (a == 0 || a > 2) return false;
        if (a == 2 && b > 6) return false;
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
