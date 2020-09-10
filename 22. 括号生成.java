class Solution {
    //本题为搜索回溯算法，关键是如何过滤掉无效的组合而提高效率
public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        generate(res, "", 0, 0, n);
        return res;
    }
        
    public void generate(List<String> res , String ans, int count1, int count2, int n){
        
        if(count1 > n || count2 > n) return;
        if(count1 == n && count2 == n)  res.add(ans);
        if(count1 >= count2){
            //关键在于回溯如何剪枝，此问题中的条件就是只有左括号数大于等于右括号数时此字符串才是有效的
            String ans1 = new String(ans);
            generate(res, ans+"(", count1+1, count2, n);
            generate(res, ans1+")", count1, count2+1, n);
        }
    }
}