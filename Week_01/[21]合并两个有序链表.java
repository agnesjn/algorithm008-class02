//将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode tmp = null;
        if (l1.val > l2.val) {
            tmp = l1;
            l1 = l2;
            l2 = tmp;
        }
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null && p2 != null) {
            if (p1.next == null || p2.val >= p1.val && p2.val < p1.next.val) {
                ListNode nextNode = new ListNode(p2.val);
                ListNode temp = p1.next;
                p1.next = nextNode;
                nextNode.next = temp;
                p2 = p2.next;
            }
            p1 = p1.next;
        }
        return l1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
