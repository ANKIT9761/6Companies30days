// link- https://leetcode.com/problems/knight-probability-in-chessboard/

class Solution {
    public double knightProbability(int n, int k, int row, int column) {
        if(k == 0){
            return 1;
        }
        return dfs(row, column, k, n, new Double[n][n][k + 1]);
    }

    public double dfs(int r, int c, int k, int n, Double[][][] cache){
        if(r < 0 || r >= n || c < 0 || c >= n){
            return 0;
        }
        if(k == 0){
            return 1;
        }
        if(cache[r][c][k] != null){
            return cache[r][c][k];
        }
        double d1 = dfs(r - 1, c + 2, k - 1, n, cache);
        double d2 = dfs(r - 1, c - 2, k - 1, n, cache);
        double d3 = dfs(r + 1, c + 2, k - 1, n, cache);
        double d4 = dfs(r + 1, c - 2, k - 1, n, cache);
        double d5 = dfs(r + 2, c - 1, k - 1, n, cache);
        double d6 = dfs(r - 2, c - 1, k - 1, n, cache);
        double d7 = dfs(r + 2, c + 1, k - 1, n, cache);
        double d8 = dfs(r - 2, c + 1, k - 1, n, cache);
        double count = d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8;
        double poss = count * 0.125;
        cache[r][c][k] = poss;
        return poss;
    }
}


