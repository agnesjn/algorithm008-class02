//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    Map<Character, Integer> charMap = new HashMap();
    public List<List<String>> groupAnagrams(String[] strs) {
        List<int[]> mapList = new ArrayList();
        List<List<String>> res = new ArrayList();
        for(String str : strs) {
            int[] list = new int[26];
            for(int i = 0; i < str.length(); i++) {
                list[(int)str.charAt(i)-97]++;
            }
            if (mapList != null) {
                int cnt = 0;
                boolean flag = false;
                for(int[] cmpList : mapList) {
                    if (arrayCmp(cmpList, list)) {
                        res.get(cnt).add(str);
                        flag = true;
                        break;
                    }
                    cnt++;
                }
                if (!flag) {
                    mapList.add(list);
                    List<String> newList = new ArrayList();
                    newList.add(str);
                    res.add(newList);
                }
            }
            else {
                mapList.add(list);
                List<String> newList = new ArrayList();
                newList.add(str);
                res.add(newList);
            }
        }
        return res;
    }

    boolean arrayCmp(int[] arr1, int[] arr2) {
        for(int i = 0; i < 26; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
