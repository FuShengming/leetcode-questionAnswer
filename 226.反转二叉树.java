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
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return root;
        }
        swap(root);
        return root;
    }
    public void swap(TreeNode root){
        if(root==null){return;}
        TreeNode tempL = root.left;
        root.left = root.right;
        root.right = tempL;
        swap(root.left);
        swap(root.right);
    }
}