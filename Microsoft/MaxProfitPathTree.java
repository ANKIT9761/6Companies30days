// link- https://leetcode.com/problems/most-profitable-path-in-a-tree/

class Solution {
    HashMap<Integer,ArrayList<Integer>> graph;
    int max = Integer.MIN_VALUE;
    ArrayList<Integer> path = new ArrayList<>();
    
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        graph = new HashMap<>();
        for(int[] e : edges){
            graph.computeIfAbsent(e[0], x -> new ArrayList<>()).add(e[1]);
            graph.computeIfAbsent(e[1], x -> new ArrayList<>()).add(e[0]);
        }
        
        //step 1 find the path from bob to 0
        path(bob , 0, new ArrayList<>(){{add(bob);}}, new HashSet<>(){{add(bob);}});
        
        //now we have the path starting from bob as bob will also start with alice bob will reach before the mid element , and will have all the profit so make them 0 as alice can't have it
        for(int i = 0; i < path.size() / 2; i++)
            amount[path.get(i)] = 0;
        
        //now there might be the case that in path there are even or odd elements if odd then the mid will be divided among bob and alice so make it half if odd only
        if(path.size() % 2 != 0)
            amount[path.get(path.size() / 2)] /= 2;
        
        //now we have preprocessed bob to alice path now just do dfs for alice to get the ans
        DFS(0, amount, new HashSet<>(){{ add(0); }}, amount[0]);
        return max;
    }
    
    //1. method to find the path from BOB to ALICE(0)
    public boolean path(int current , int target, ArrayList<Integer> route, HashSet<Integer> visited){
        //if we reach from bob to alice posn i.e is 0 record the path
        if(current == target){
            path = new ArrayList<>(route);
            return true;
        }
        
        for(int nbr : graph.getOrDefault(current, new ArrayList<>())){
            if(visited.contains(nbr))
                continue;
            route.add(nbr);
            visited.add(nbr);
            
            //path found then return from here only
            if(path(nbr, target, route, visited) == true)
                return true;
            
            //backtrack if path not found
            visited.remove(nbr);
            route.remove(route.size() - 1);
        }
        
        return false;
    }
    
    //2. method to traverse from alice to leaf and find the max profit possible
    public void DFS(int current, int[] amount, HashSet<Integer> visited, int profit){
        boolean leaf = true;
        
        for(int nbr : graph.getOrDefault(current, new ArrayList<>())){
            if(visited.contains(nbr) == true)
                continue;
            
            //as not visited so current is not leaf
            leaf = false;
            visited.add(nbr);
            DFS(nbr, amount, visited, profit + amount[nbr]);
        }
        
        if(leaf)
            max = Math.max(max, profit);
        
    }
}
