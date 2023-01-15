// link- https://leetcode.com/problems/number-of-people-aware-of-a-secret/

class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int[] dp=new int[n+1];
        int mod=1000000007;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            if(i<=delay)
                dp[i]=dp[i-1];
            else if(i<=forget)
                dp[i]=(dp[i-1]+dp[i-delay])%mod;
            else
                dp[i]=(dp[i-1]+(dp[i-delay]-dp[i-forget]+mod)%mod)%mod;
        }
        return (dp[n]-dp[n-forget]+mod)%mod;
    }
}
