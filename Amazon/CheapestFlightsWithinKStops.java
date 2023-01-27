// link- https://leetcode.com/problems/cheapest-flights-within-k-stops/

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] distance = new int[n];

        // reverse distance since we want to update smaller distance AFTER we update the larger distance
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->distance[b[0]]-distance[a[0]]);

        HashMap<Integer,List<int[]>> graph = new HashMap<>();

        for(int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            graph.put(i, new ArrayList<>());
        }

        for(int[] flight : flights) {
            List<int[]> neighbors = graph.get(flight[0]);
            neighbors.add(new int[]{flight[1], flight[2]});
        }

        distance[src] = 0;
        pq.add(new int[]{src, k, 0}); // node, remaining stops, path length

        while(!pq.isEmpty()) {
            int[] front = pq.poll();

            for(int[] neighbor : graph.get(front[0])) {
                if(distance[neighbor[0]] > front[2] + neighbor[1] && front[1] >= 0) {
                    distance[neighbor[0]] = front[2] + neighbor[1];
                    pq.add(new int[]{neighbor[0], front[1]-1, distance[neighbor[0]]});
                }
            }
        }

        return distance[dst] == Integer.MAX_VALUE ? -1 : distance[dst];
    }
}
