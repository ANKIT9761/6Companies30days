// link- https://leetcode.com/problems/destroying-asteroids/

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long ma=mass;
        for(int i:asteroids){
            if(ma>=i) ma+=i;
            else return false;
        }
        return true;
    }
}
