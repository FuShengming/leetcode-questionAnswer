/*
给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。

示例 1：

输入：
    3
   / \
  9  20
    /  \
   15   7
输出：[3, 14.5, 11]
解释：
第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
提示：

节点值的范围在32位有符号整数范围内。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/average-of-levels-in-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

/*
此题为遍历二叉树，用递归遍历即可。
而要求计算层平均值，只需要在递归的过程中传递层数信息即可。
需要注意每层的和可能会超过Integer的上限。
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        if(root==null){return  new ArrayList<Double>();}
        List<Long> sums = new ArrayList<Long>();
        //sums记录下标所对应的层的节点值和
        List<Integer> cnts = new ArrayList<Integer>();
        //cnts记录下标对应的层的节点数
        search(root,sums,cnts,0);
        List<Double> res = new ArrayList<Double>();
        if(cnts.size()==sums.size()){
            for(int i=0;i<cnts.size();i++){
                double temp = (double)sums.get(i)/(double)cnts.get(i);
                res.add(temp);
            }
        }
        return res;
    }
    public void search(TreeNode root,List<Long> sums,List<Integer> cnts,int layer){
        if(root==null){
            return;
        }
        if(sums.size()<=layer&&cnts.size()<=layer){
        //如果是第一次到达此层
            sums.add(layer,(long)root.val);
            cnts.add(layer,1);
        }
        else{
            sums.set(layer,sums.get(layer)+(long)root.val);
            cnts.set(layer,cnts.get(layer)+1);
        }
        search(root.left,sums,cnts,layer+1);
        search(root.right,sums,cnts,layer+1);
    }
}