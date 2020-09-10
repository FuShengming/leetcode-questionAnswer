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