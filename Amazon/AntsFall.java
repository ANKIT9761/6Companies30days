// link- https://leetcode.com/problems/last-moment-before-all-ants-fall-out-of-a-plank/

class Solution {
    public int getLastMoment(int n, int[] left, int[] right)
    {
        int ans=Integer.MIN_VALUE;
        
        if(left.length>0)
        {
            for(int i:left)
            {
                ans=Math.max(ans,i);
            }
        }
        
        if(right.length>0)
        {
            for(int i:right)
            {
                ans=Math.max(ans,n-i);
            }
        }
        return ans;
    }
}
