// link- https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/

// helper link- https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/discuss/3033016/EASIEST-JAVA-SOLUTION-EVER-!!!-(-Brute-Force-Approach-and-Better-Pair-Approach)-2-Approaches-!!!

public class Pair{
    int f,s;
    Pair(int f, int s){
        this.f = f;
        this.s = s;
    }
}

class Solution {

    int count;
    public int averageOfSubtree(TreeNode root) {
        count = 0;
        isAverageBT(root);
        return count;
    }

    public Pair isAverageBT(TreeNode root){
        if(root==null){
            return new Pair(0,0);
        }
        Pair left = isAverageBT(root.left);
        Pair right = isAverageBT(root.right);
        int sum = root.val + left.f + right.f;
        int number = 1 + left.s + right.s;
        if(sum/number == root.val){
            count++;
        } 
        return new Pair(sum,number);
    }

}
