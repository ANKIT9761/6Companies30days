// link- https://leetcode.com/problems/maximum-consecutive-floors-without-special-floors/

class Solution {
    public int maxConsecutive(int bottom, int top, int[] special) {
        int maxFloors=0;
        int start=bottom;
        Arrays.sort(special);
        for(int i=0;i<special.length;i++){
            maxFloors=Math.max(maxFloors,special[i]-start);
            start=special[i]+1;
        }
        maxFloors=Math.max(maxFloors,top-start+1);
        return maxFloors;
    }
}
