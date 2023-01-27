// link- https://leetcode.com/problems/shuffle-an-array/

class Solution {
    int[] arr;

    public Solution(int[] nums) {
        arr = nums;
    }
    
    public int[] reset() {
        return arr;
    }
    
    public int[] shuffle() {
        int[] suff = arr.clone();
        for(int i = suff.length-1;i>=0;i--) {
            int index = new Random().nextInt(i+1);
            int temp = suff[i];
            suff[i] = suff[index];
            suff[index] = temp;
        }
        return suff;
    }
}
