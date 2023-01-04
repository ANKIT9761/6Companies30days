// link- https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/

class Solution {
    public int countPaths(int n, int[][] roads) {
        int mod  = (int)(1e9+7);

        //creating the adjacency list
        List<List<Pair>> adj = new ArrayList<>();
        int m = roads.length;
        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i<m; i++){
            adj.get(roads[i][0]).add(new Pair(roads[i][1], roads[i][2]));
            adj.get(roads[i][1]).add(new Pair(roads[i][0], roads[i][2]));
        }

        //time array is required to store the min time to reach each node
        int [] time = new int[n];
        //ways array stores the number of ways to reach a node
        long [] ways = new long[n];
        Arrays.fill(time, (int)1e18);//fill with a large value
        
        time[0] = 0;//time to reach source node
        ways[0] = 1;//number of ways to reach source node

        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.first - y.first);
        pq.add(new Pair(0, 0));//{time, node}

        while(!pq.isEmpty()){
            Pair out = pq.peek();
            int currTime = out.first;
            int node = out.second;
            pq.remove();

            for(Pair val : adj.get(node)){
                int adjNode = val.first;
                int weight = val.second;

                if(weight + currTime < time[adjNode]){
                    time[adjNode] = weight+currTime;
                    pq.add(new Pair(time[adjNode], adjNode));
                    ways[adjNode] = ways[node];//ways to reach the adjacent node and parent node will be the same if adjNode has not been visited before or better time is found
                }else if(weight + currTime == time[adjNode]){//if a node is reached again with the same time as stored in the distance array then don't add it to the queue again and ways to reach that node will be updated in the following way
                    ways[adjNode] = (ways[adjNode]+ways[node])%mod;
                }
            }
        }
        return (int)ways[n-1];
    }
}

class Pair{
    int first, second;
    public Pair(int _first, int _second){
        this.first = _first;
        this.second = _second;
    }
}
