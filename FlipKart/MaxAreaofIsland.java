// link- https://leetcode.com/problems/max-area-of-island/

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int count=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    count=Math.max(count,helper(grid, i, j));
                }
            }
        }
        return count;
    }
    public int helper(int[][] grid, int i, int j){

        if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]!=1){
            return 0;
        }
        int count=1;
        grid[i][j]=2;   
        count+=helper(grid, i+1, j);
        count+=helper(grid, i-1, j);
        count+=helper(grid, i, j+1);
        count+=helper(grid, i, j-1);
        return count;
    }
}
