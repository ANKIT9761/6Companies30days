// link- https://leetcode.com/problems/generate-random-point-in-a-circle/

// helper link- https://leetcode.com/problems/generate-random-point-in-a-circle/discuss/1113623/JS-Python-Java-C%2B%2B-or-Polar-Notation-Solution-w-Explanation

class Solution {
    double RAD, XC, YC;
    public Solution(double radius, double x_center, double y_center) {
        RAD = radius;
        XC = x_center;
        YC = y_center;
    }
    public double[] randPoint() {
        double ang = Math.random() * 2 * Math.PI,
            hyp = Math.sqrt(Math.random()) * RAD,
            adj = Math.cos(ang) * hyp,
            opp = Math.sin(ang) * hyp;
        return new double[]{XC + adj, YC + opp};
    }
}
