// link- https://leetcode.com/problems/new-21-game/

// helper link- https://leetcode.com/problems/new-21-game/discuss/1902112/Java-from-O(n2)-to-O(n)

class Solution {
    public double new21Game(int n, int k, int maxPts) {
        if (k == 0){
            return 1;
        }

        double[] dp = new double[k + maxPts];
        double sum = 1;
        dp[0] = 1;
        for (int i = 1; i < k + maxPts; i++){
            dp[i] = sum / maxPts;
            if (i >= maxPts){
                sum -= dp[i - maxPts];
            }
            if (i < k){
                sum += dp[i];
            }
        }

        double ans = 0;
        for (int i = k; i < k + maxPts && i <= n; i++){
            ans += dp[i];
        }

        return ans;
    }
}

/*
2nd Approach
This solution uses dynamic programming to calculate the probability that Alice will get a score of K or greater in the game. 
The key insight is that the probability of getting a score of i depends only on the probability of getting a score of i+1, i+2, ... ,
 i+W, since those are the only scores that can be reached from i in one draw.

The code starts by initializing an array dp of length K+W, where dp[i] represents the probability of getting a score of i. 
For all i between K and N (inclusive) and i less than K+W, the probability is set to 1.0, since getting a score of i or 
higher is guaranteed in these cases.

Then, it loops through i from K-1 down to 0, updating the probability of getting a score of i based on the probability of 
getting a score of i+1, i+2, ... , i+W. The variable S keeps track of the sum of the probability of getting a score of i+1,
 i+2, ... , i+W. The probability of getting a score of i is set to S/W, and S is updated to account for the new probability.

Finally, the code returns dp[0], which is the probability of getting a score of 0 or higher, which is the same as the probability
 of getting a score of K or higher.

*/

class Solution {
    public double new21Game(int N, int K, int W) {
        if (K == 0) return 1.0;
        double[] dp = new double[K + W];
        for (int i = K; i <= N && i < K + W; i++)
            dp[i] = 1.0;
        double S = Math.min(N - K + 1, W);
        for (int i = K - 1; i >= 0; i--) {
            dp[i] = S / W;
            S += dp[i] - dp[i + W];
        }
        return dp[0];
    }
}
