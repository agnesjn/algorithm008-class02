class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> nodeQueue = new LinkedList();
        List<List<Integer>> res = new ArrayList();
        if (root == null) return res;
        nodeQueue.add(root);
        while (nodeQueue.size() > 0) {
            List<Integer> level = new ArrayList();
            int size = nodeQueue.size();
            for(int i = 0; i < size; i++) {
                Node node = nodeQueue.poll();
                nodeQueue.addAll(node.children);
                level.add(node.val);
            }
            res.add(level);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
