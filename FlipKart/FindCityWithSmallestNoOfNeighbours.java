// link- https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/

// Dijkstra Single source distance algo method-

class Solution {
    class Pair{
        int v; int w;
        Pair(int v, int weight){
            this.v=v; this.w=weight;
        }
    }
    public int findTheCity(int n, int[][] edges, int threshDist) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        } 

        for(int i=0; i<edges.length; i++){
            int u=edges[i][0];
            int v=edges[i][1];
            int w=edges[i][2];
            adj.get(u).add(new Pair(v,w));  
            adj.get(v).add(new Pair(u,w));           
        } 

        int ans = Integer.MAX_VALUE; int node = 0;
        for(int i=0; i<n; i++){
            int val=calculateDist(adj,i,threshDist);
            if(val<=ans){
                ans=val; node=i;
            }                        
        }        
        return node;
    }

    int calculateDist(ArrayList<ArrayList<Pair>> adj, int src, int threshDist){
        int n=adj.size();

        int dist[] = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);        
        dist[src]=0;

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(src);

        while(!q.isEmpty()){
            int u = q.poll();
            for(Pair ajd: adj.get(u)){
                if(dist[ajd.v]>(dist[u]+ajd.w)){
                    dist[ajd.v]=dist[u]+ajd.w;
                    q.add(ajd.v);
                }
            }
        }

        int count=0;
        for(int i=0; i<n; i++){
            if(dist[i]<=threshDist) count++;
        }
        return count-1;
    }
}

// Floyd Warshal Multiple source Algorithm

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int [][] graph=new int[n][n];
        for(int []t:graph) Arrays.fill(t,99999);
        for(int i=0;i<n;i++) graph[i][i]=0;
        for(int i=0;i<edges.length;i++){
            graph[edges[i][0]][edges[i][1]]=edges[i][2];
            graph[edges[i][1]][edges[i][0]]=edges[i][2];
        }

// flyod warshal distance calculation algo 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    graph[j][k]=Math.min(graph[j][k],graph[j][i]+graph[i][k]);
                }
            }
        }

//Find City with Least Connections

        int min=Integer.MAX_VALUE,max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            int count=0;
            for(int j=0;j<n;j++){
                if(graph[i][j]!=0 && graph[i][j]<=distanceThreshold) count++;
            }
            if(min>count){
                max=i;
                min=count;
            }
            else if(min==count) max=Math.max(max,i);
        }
        return max;
    }
}
