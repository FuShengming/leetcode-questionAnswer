/*
2020-09-10 
提交一次 3ms 打败88.7%

40.组合总和
给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int n = candidates.length;
         List<List<Integer>> res = new ArrayList<>();
        if(n==0){
            return res;
        }
        Arrays.sort(candidates);
        search(candidates,target,0,res,new ArrayList<Integer>(),n);
        return  res;
    }
    public void search(int[] candidates,int target,int start,List<List<Integer>> res,List<Integer> li,int len){
        
        for(int i = start;i < len;i++){
            
            if(candidates[i]==target){
                li.add(candidates[i]);
                res.add(new ArrayList<Integer>(li));
                li.remove(li.size()-1); //注意回溯，要删除这次搜索添加的元素
               
            }
            else if(candidates[i]>target){
                return;
            }
            else{
                li.add(candidates[i]);
                dfs(candidates,target-candidates[i],i+1,res,li,len);
                li.remove(li.size()-1);
                //循环跳过相同元素以避免重复组合
                while(i<len-1&&candidates[i]==candidates[i+1]){
                    i++;
                }
            }
        }
    }
}
