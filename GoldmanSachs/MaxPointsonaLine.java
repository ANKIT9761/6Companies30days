// link- https://leetcode.com/problems/max-points-on-a-line/

class Solution {
    public int maxPoints(int[][] points) {
        int max = 0;
        if(points.length <= 2) 
            return points.length;
    
        for(int i = 0; i < points.length; i++){
            Map<Double, Integer> hmap = new HashMap<>(); 
            for(int j = 0; j < points.length; j++){
                if(i == j)  continue;

                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                double slope = (double)(y2 - y1)/(double)(x2 - x1);
            
                hmap.put(slope, hmap.getOrDefault(slope, 1) + 1);
                max = Math.max(max, hmap.get(slope));
            }
        }
        return max;
    }
}
