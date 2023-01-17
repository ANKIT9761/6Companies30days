// link- https://leetcode.com/problems/partition-to-k-equal-sum-subsets/

// helper for bit- https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/3033734/Backtrack-and-visited-bit-Memo

class Solution {
    Map<String, Boolean> memo;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        if (totalSum % k != 0) {
            return false;
        }

        int target = totalSum / k;
        boolean[] visited = new boolean[nums.length];
        memo = new HashMap<>();
        return backtrack(nums, k, 0, target, visited, 0);
        
    }

    private boolean backtrack(int[] nums, int k, int index, int target, boolean[] visited, int currentSum){
        if (k == 0) {
            return true;
        }

        String state = Arrays.toString(visited);

        if (currentSum == target) {
            boolean result = backtrack(nums, k - 1, 0, target, visited, 0);
            memo.put(state, result);
            return result;
        }

        if (memo.containsKey(state)) {
            return memo.get(state);
        }

        for (int i = index; i < nums.length; i++) {
            if (visited[i] || currentSum + nums[i] > target) {
                continue;
            }
            currentSum += nums[i];
            visited[i] = true;
            if (backtrack(nums, k, i + 1, target, visited, currentSum)) {
                return true;
            } 

            currentSum -= nums[i];
            visited[i] = false;

        }

        return false;

    }
}

// bit approach

class Solution {
    Map<Integer, Boolean> memo;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        if (totalSum % k != 0) {
            return false;
        }

        int target = totalSum / k;
        int visited = 0;
        memo = new HashMap<>();
        return backtrack(nums, k, 0, target, visited, 0);
        
    }

    private boolean backtrack(int[] nums, int k, int index, int target, int visited, int currentSum){
        if (k == 0) {
            return true;
        }

        

        if (currentSum == target) {
            boolean result = backtrack(nums, k - 1, 0, target, visited, 0);
            memo.put(visited, result);
            return result;
        }

        if (memo.containsKey(visited)) {
            return memo.get(visited);
        }

        for (int i = index; i < nums.length; i++) {
            if (((visited >> i) & 1) == 1 || currentSum + nums[i] > target) {
                continue;
            }
            currentSum += nums[i];
            visited |= 1 << i; 
            if (backtrack(nums, k, i + 1, target, visited, currentSum)) {
                return true;
            } 

            currentSum -= nums[i];
            visited ^= 1 << i; 

        }

        return false;

    }
}
