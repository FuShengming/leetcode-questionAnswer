/*
给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/permutations-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

/*
看到求所有的...问题，考虑使用回溯算法。
思路为先排序数组，在回溯的遍历数组，每次选择一个数字加入代表当前排列的数组，并将此数字此数组中删除。
然后判断原数组是否已空（数字已经全被使用一遍），若已空则现在已经找到一种排列，则加入结果数组中（注意深复制）。
若没空则递归回溯，将已经进行过删除的原数组传入下一轮递归。
注意，递归回溯后要删除刚添加（选择）的元素以还原数组状态进行下一轮循环遍历。且因为每次递归前，已将选中元素从原数组中删除，所以当递归结束后回溯时要将这个元素重新加入原数组的原位置。

因为已经排序数组，所以可以直接在每次循环最后解决重复元素问题。
因为我们是每次循环选定当前位置的元素，所以当有重复元素时，相当于对当前位置选择好几次同样的元素而造成结果重复。
因为数组已排序，所有相同元素都是相邻的，所以直接在每次循环的最后将所有相同元素跳过即可。
*/
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if(nums.length==0){return new ArrayList<List<Integer>>();}
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> cur =  new ArrayList<Integer>();
        for(int i=0;i<nums.length;i++){cur.add(nums[i]);}
        recall(cur,new ArrayList<Integer>(),res,nums.length);
        return res;
    }
    public void recall(List<Integer> nums,List<Integer> cur,List<List<Integer>> res,int len){
            for(int i=0;i<len;i++){
                cur.add(nums.get(i));
                if(nums.size()==1){
                    res.add(new ArrayList<Integer>(cur));
                }
                else{
                    int temp = nums.get(i);
                    nums.remove(i);//选中当前元素，并将其从原数组中删除以进行递归
                    recall(nums,cur,res,len-1);
                    nums.add(i,temp);//递归结束后将选择元素重新加入原数组的原位置
                }
                cur.remove(cur.size()-1);//还原当前数组状态已进行下一轮循环
                while(i<len-1&&nums.get(i)==nums.get(i+1)){
                    i++;
                } 
            }
    }
}