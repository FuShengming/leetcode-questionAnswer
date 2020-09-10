/*
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
（手机九键键盘）


示例:

输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

/*
本体为找出所有的.... ， 则考虑使用回溯算法。回溯算法很多时候用于有“组合”类问题的题目。
*/

class Solution {
    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        if(n==0){return new ArrayList<String>();}
        List<String> res = new ArrayList<>();
         Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        func(digits,0,res,"",phoneMap);
        
        return res;
    }
    public void func(String digits,int index,List<String> res,String cur,Map<Character,String> m){
        String temp = m.get(digits.charAt(index));
        int n = digits.length();
        int len = temp.length();
        for(int i=0;i<len;i++){
            cur+=(temp.charAt(i)+"");
            if(index==n-1){
                res.add(cur);
                cur=cur.substring(0,cur.length()-1);
                //注意每次加完res要还原当前字符串
            }
            else{
                func(digits,index+1,res,cur,m);
                cur=cur.substring(0,cur.length()-1);
                //回溯完也要还原字符串
            }
        }
    }
}