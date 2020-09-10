/*
给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。

 

例如，给定三角形：

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

 

说明：

如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/triangle
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    /*注：此代码为正向遍历，还有其他思路为从最后一层开始向上遍历，比此方法方便。另外此方法空间复杂度为n2，n的方法为观察状态相关性，发现dp[i][j]只与dp[i-1][...]相关，所以实际上我们不用存储其他的状态。具体可以用两个一维数组实现。*/
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if(n==0){return 0;}
        int[][] dp = new int[n][n];
        //动态规划数组，每个元素代表到达三角形中此下标随用的最短路径和
        dp[0][0]=triangle.get(0).get(0);

        for(int i=1; i<n; i++){
            for(int j = 0; j <= i; j++){
                if(j==0){
                    //该层第一个元素只能由上一层第一个元素到达
                    dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
                }
                else if(j==i){
                    //该层最后一个元素只能由上一层最后一个元素到达
                    dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
                }
                else{
                    //普通元素可以由上一层相邻两元素到达，选最优
                    dp[i][j] = (dp[i-1][j] < dp[i-1][j-1] ? dp[i-1][j]:dp[i-1][j-1])+triangle.get(i).get(j);
                }
            }
        }
        Arrays.sort(dp[n-1]);
        return dp[n-1][0];
    }
}