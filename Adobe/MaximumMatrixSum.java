// link- https://leetcode.com/problems/maximum-matrix-sum/

// helper- https://leetcode.com/problems/maximum-matrix-sum/discuss/3036729/Easy-solution-C%2B%2B-JAVA-PYTHON-oror-Beginner-Friendly-oror-Best-Method

class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int c=0, min=Integer.MAX_VALUE;
        long sum=0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]<=0) c++; 
                matrix[i][j]=Math.abs(matrix[i][j]);
                sum+=matrix[i][j];
                min=Math.min(min, matrix[i][j]);
            }
        }
        if(c%2==1) sum-=(2*min);
        return sum;
    }
}
