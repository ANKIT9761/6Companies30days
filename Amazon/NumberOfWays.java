// link- https://leetcode.com/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps/

// helper link- https://leetcode.com/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps/discuss/2527381/JavaC%2B%2BPython-Math-Solution-O(klogk)

class Solution {
     int p = 1000000007;
    public int numberOfWays(int a, int b, int k) {
        if ((a - b - k) % 2 != 0) return 0;
        if (Math.abs(a - b) > k) return 0;
        long res = 1L;
        for (int i = 0; i < (b - a + k) / 2; ++i) {
            res = res * (k - i) % p;
            res = res * inv(i + 1) % p;
        }
        return (int)res;
    }

    private long inv(long a) {
        if (a == 1) return 1;
        return (p - p / a) * inv(p % a) % p;
}
}

// 2nd dp approach

class Solution {
    public int numberOfWays(int start, int end, int k) {
        int[][] dp = new int[2*(start+k)][k+1];                      
		for (int[] a : dp) Arrays.fill(a, -1);
		int maxDistance = Math.abs(start-k);           // calculate max distance starting from start upto K steps
        return ways(start+maxDistance, end+maxDistance, k, dp);
    }
	
    int mod = 1000_000_007;
    int ways(int s, int e, int k, int[][] dp) {
		if (k==0) {                                             // no more steps left
			if (s==e) return 1;                                 // if we reached end, then return 1
			else return 0;                                      // else return 0
		}
		
		if (dp[s][k] != -1) return dp[s][k];
		
		int left = ways(s-1, e, k-1, dp);
		int right = ways(s+1, e, k-1, dp);
		
		return dp[s][k] = (left + right)%mod;
	}
}
