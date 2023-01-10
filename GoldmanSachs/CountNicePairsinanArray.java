// link- https://leetcode.com/problems/count-nice-pairs-in-an-array/

class Solution {
    //nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])  => nums[i] - rev(nums[i] = nums[j] -rev(nums[j])

    public int countNicePairs(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int res = 0;
        int mod = (int)Math.pow(10,9)+7;
        for(int i=0;i<nums.length;i++){
            int revNo =  Integer.parseInt(new StringBuffer(String.valueOf(nums[i])).reverse().toString());
            res = (res + map.getOrDefault(nums[i] - revNo,0)) % mod;
            map.put(nums[i] - revNo,map.getOrDefault(nums[i] - revNo,0)+1);
        }
    
        
        return res;
    }
}
