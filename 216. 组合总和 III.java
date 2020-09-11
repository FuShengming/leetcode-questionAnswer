/*
找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

说明：

所有数字都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: k = 3, n = 7
输出: [[1,2,4]]
示例 2:

输入: k = 3, n = 9
输出: [[1,2,6], [1,3,5], [2,3,4]]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

//本题相比于其他组合之和主要就是添加了k个数字的限制，那么只要把k填入回溯方法的判断条件即可。
//0ms 击败100%
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        if(n==0||k==0){return new ArrayList<List<Integer>>();}
        int[] nums = {1,2,3,4,5,6,7,8,9};
        List<List<Integer>> res = new ArrayList<>();
        search(nums,n,0,new ArrayList<Integer>(),res,k);
        return res;
    }
    public void search(int[] nums,int target,int start,ArrayList<Integer> cur,List<List<Integer>> res,int k){
        for(int i=start;i<nums.length;i++){
            if(nums[i]==target&&k==1){
                cur.add(nums[i]);
                res.add(new ArrayList<Integer>(cur));
                cur.remove(cur.size()-1);
            }
            else if(nums[i]<target&&k>1){
                cur.add(nums[i]);
                search(nums,target-nums[i],i+1,cur,res,k-1);
                cur.remove(cur.size()-1);
            }
        }
    }
}