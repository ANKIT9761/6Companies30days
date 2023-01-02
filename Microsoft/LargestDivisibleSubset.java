// link- https://leetcode.com/problems/largest-divisible-subset/

// problem similar to longest increasing subset 

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        
       	int dp[] = new int[nums.length];
        
  	    int maximum = 0;
	for (int i = 0; i < nums.length; i++){
        int max=0;
		for (int j = 0; j < i; j++){
			if (nums[i] % nums[j] == 0) {
				if(dp[j]>max)max=dp[j];
			}
        }
        dp[i]=max+1;
        if(dp[i]>maximum) maximum=dp[i];
    }    
        
	List<Integer> ans=new ArrayList<>();
    int prev = -1; 
	for (int i = dp.length - 1; i >= 0; i--){
		if (dp[i] == maximum &&(prev == -1 || prev % nums[i] == 0)) {
			ans.add(nums[i]);
			maximum--;
			prev = nums[i];
		}
    }
        
	return ans;
    }
}
