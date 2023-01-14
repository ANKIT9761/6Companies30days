// link- https://leetcode.com/problems/shortest-unsorted-continuous-subarray/

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        if(nums.length == 0) return 0;

        for(int i = 0; i < nums.length; i++){
            if(i == 0) {
                if(nums.length > 1 && nums[i] > nums[i+1]){
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);
                }
            } else if(i == nums.length-1) {
                if(nums[i] < nums[i-1]){
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);
                }
            } else {
                if(nums[i] > nums[i+1] || nums[i] < nums[i-1]){
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);
                }
            }
        }
        if(min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) return 0;
        int i, j;
        for(i = 0; i < nums.length && nums[i] <= min; i++);
        for(j = nums.length-1; j >= 0 && nums[j] >= max; j--);

        return j-i+1;
    }
}
