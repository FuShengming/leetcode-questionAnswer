/*
给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

"123"
"132"
"213"
"231"
"312"
"321"
给定 n 和 k，返回第 k 个排列。

说明：

给定 n 的范围是 [1, 9]。
给定 k 的范围是[1,  n!]。
示例 1:

输入: n = 3, k = 3
输出: "213"
示例 2:

输入: n = 4, k = 9
输出: "2314"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/permutation-sequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

/*
本题实际是一道数学推理题。可设想，对于指定的n，有n!种排列，而这些排列实际上是由:
1开头，加上剩下数字的(n-1)!种派列
2开头，加上剩下数字的(n-1)!种派列
....
n开头，加上剩下数字的(n-1)!种派列
组成的。
则原数组为{1，2，3，...，n}

则可知，第k种排列的第一个数字在原数组的下标（第几个）：
当k%(n-1)!不为0即余数不为0时，为k/(n-1)! ,将k赋值为余数
当k%(n-1)!为0即余数为0时，为k/(n-1)!-1，将k赋值为(n-1)!
（余数为0说明此k对应的派列为k/(n-1)!-1开头的最后一个，而非k/(n-1)!开头）
最后把已选用的数字从原数组中删除。

遵循上述的逻辑，原k对应的排列的第二个数字在原数组的下标（第几个）：
k(已按照上述逻辑重新赋值)%(n-2)!不为0即余数不为0时，为k/(n-2)! ,将k赋值为余数
当k(已按照上述逻辑重新赋值)%(n-2)!为0即余数为0时，为k/(n-2)!-1，将k赋值为(n-1)!
最后把已选用的数字从原数组中删除。

此逻辑可以用循环n递减实现，这样就能一个个找到k对应的排列的每一位数字是什么。
*/
class Solution {
    public String getPermutation(int n, int k) {
        if(n==0){return "";}
        ArrayList<Integer> avail = new ArrayList<>();
        for(int i=1;i<=n;i++){
            avail.add(i);
        }
        StringBuilder sb = new StringBuilder();
        int rest = k;
        while(n>0){
            int pre = func(n-1);
            int basic = rest/pre;
            rest = rest%pre;
            if(rest==0){
                basic-=1;
                rest=pre;
            }
            sb.append(avail.get(basic));
            avail.remove(basic);
            n--;
        }
        return sb.toString();
        
    }
    public int func(int n){
        int res = 1;
        for(int i=n;i>1;i--){
            res*=i;
        }
        return res;
    }
                
    }
