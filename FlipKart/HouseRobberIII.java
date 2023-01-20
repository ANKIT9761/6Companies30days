// link- https://leetcode.com/problems/house-robber-iii/

class Solution {
    public int rob(TreeNode root) {
        int[] result = getMax(root);
        return Math.max(result[0], result[1]);
    }
    public int[] getMax(TreeNode root)
    {
        if(root == null)
            return new int[2];
        int[] left = getMax(root.left);
        int[] right = getMax(root.right);

        int withRoot = left[1]+ right[1] + root.val;
        int withoutRoot = Math.max(left[0], left[1])+ Math.max(right[0], right[1]);
        return new int[]{withRoot,withoutRoot };
    }
}
