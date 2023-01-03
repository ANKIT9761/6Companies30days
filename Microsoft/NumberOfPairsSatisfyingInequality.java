// link- https://leetcode.com/problems/number-of-pairs-satisfying-inequality/

// This problem was hard for me had to take help i guessed the intutuion right but was unable to form a solution.

// help link1- https://leetcode.com/problems/number-of-pairs-satisfying-inequality/discuss/2656383/Java-or-2-solutions-or-Binary-Search-or-Binary-Index-Tree-or-merge-sort

// help link2- https://leetcode.com/problems/number-of-pairs-satisfying-inequality/discuss/2648104/2-O(nlogn)-solutions-or-Merge-Sort-and-BIT-or-Java

class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        dif = diff;
        for (int i = 0; i < nums1.length; i++) nums1[i] -= nums2[i];
        return mergeSort(nums1, 0, nums1.length - 1);
    }
    private int dif;
    //Time: O(lgN * N); Space:O(lgN)
    private long mergeSort(int[] nums, int left, int right) {
        if (right == left) return 0;

        int mid = (left + right) / 2;
        long count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        for (int i = mid, j = right; i >= left && j >= mid + 1;){
            if (nums[i] <= nums[j] + dif) {
                count += i - left + 1;j--;
            } else i--;
        }
        merge(nums, left, mid, mid + 1, right);
        return count;
    }

    //Time: O(N); Space:O(N)
    private void merge(int[] nums, int left1, int right1, int left2, int right2){
        if (left1 == left2) return;
        int[] data = Arrays.copyOfRange(nums, left1, right1 + 1);
        int idx = 0;
        while (idx < data.length && left2 <= right2) {
            nums[left1++] = data[idx] <= nums[left2] ? data[idx++] : nums[left2++];
        }
        while (idx < data.length) nums[left1++] = data[idx++];
        while (left2 <= right2) nums[left1++] = nums[left2++];
    }
}
