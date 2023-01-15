// link- https://leetcode.com/problems/dungeon-game/

// recursion approach.

class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, Integer.MIN_VALUE));
        int val = f(dungeon, dp, 0, 0);
        if(val >=0) return 1;
        return Math.abs(val) +1;


    }

    private int f(int[][] dungeon, int[][] dp, int row, int col){
        if(row == dungeon.length -1 && col == dungeon[0].length -1){
            return dungeon[row][col] > 0 ? 0 : dungeon[row][col];
        }

        if(dp[row][col] != Integer.MIN_VALUE) return dp[row][col];

        //rightward
        int right = Integer.MIN_VALUE;
        if(col < dungeon[0].length-1) right = f(dungeon, dp, row, col+1) + dungeon[row][col];

        //downward
        int down = Integer.MIN_VALUE;
        if(row < dungeon.length-1) down = f(dungeon, dp, row+1, col) + dungeon[row][col];

        int max = Math.max(right, down);

        return dp[row][col] = max > 0 ? 0 : max;
    }


}

// dp approach

class Solution {
    /**
     * We know that we would require at-latest 1 health to go from a cell to another cell, which means the value in the cell must be greater than 1
     * to be alive.
     *
     * Keeping this in mind at each cell we should calculate what is the minimum energy that is required to a cell to enter the current cell.
     *
     * Lets consider the last cell of dungeon, if it positive which means we just need 1 minimum health for our knight to enter that cell.
     * If it is -ve we would require x+(value in the last cell)=1
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    dungeon[i][j] = dungeon[i][j] < 0 ? Math.abs(dungeon[i][j]) + 1 : 1;
                    continue;
                }

                int right = Integer.MAX_VALUE;
                int bottom = Integer.MAX_VALUE;

                if (j + 1 < n) {
                    right = dungeon[i][j + 1] - dungeon[i][j];
                    if (right <= 0) right = 1;
                }
                if (i + 1 < m) {
                    bottom = dungeon[i + 1][j] - dungeon[i][j];
                    if (bottom <= 0) bottom = 1;
                }
                dungeon[i][j] = Math.min(right, bottom);
            }

        }
        
        return dungeon[0][0];

    }
}
