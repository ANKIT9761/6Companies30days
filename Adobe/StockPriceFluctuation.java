// link- https://leetcode.com/problems/stock-price-fluctuation/

// 1st approach- 2 heaps and one map

class StockPrice {
    private HashMap<Integer, Integer> timestamp_price_map;
    private PriorityQueue<int[]> minHeap;
    private PriorityQueue<int[]> maxHeap;
    
    private int latestTimeStamp = Integer.MIN_VALUE;

    public StockPrice() {
        timestamp_price_map = new HashMap<>();
        minHeap = new PriorityQueue<>((a, b) -> a[1]-b[1]);
        maxHeap = new PriorityQueue<>((a, b) -> b[1]-a[1]);
    }
    
    public void update(int timestamp, int price) {
        timestamp_price_map.put(timestamp, price);
        minHeap.add(new int[]{timestamp, price});
        maxHeap.add(new int[]{timestamp, price});

        latestTimeStamp = Math.max(latestTimeStamp, timestamp);
    }
    
    public int current() {
        return timestamp_price_map.get(latestTimeStamp);
    }
    
    public int maximum() {
        int[] max = maxHeap.peek();
        while(timestamp_price_map.get(max[0]) != max[1]){
            maxHeap.poll();
            max = maxHeap.peek();
        }

        return max[1];
    }
    
    public int minimum() {
        int[] min = minHeap.peek();
        while(timestamp_price_map.get(min[0]) != min[1]){
            minHeap.poll();
            min = minHeap.peek();
        }

        return min[1];
    }
}

// 2nd approach treemaps

class StockPrice {
    Map<Integer, Integer> map;
    TreeMap<Integer, Integer> tmap;
    int cur = -1;
    public StockPrice() {
        map = new HashMap<>();
        tmap = new TreeMap<>();
    }
            
    public void update(int timestamp, int price) {
        if (!map.containsKey(timestamp)) {
            tmap.put(price, tmap.getOrDefault(price, 0) + 1);
        } 
        else {
            int prev = map.get(timestamp);
            tmap.put(prev, tmap.get(prev) - 1);
            if (tmap.get(prev) <= 0) tmap.remove(prev);
            tmap.put(price, tmap.getOrDefault(price, 0) + 1);

        }
        cur = Math.max(cur, timestamp);
        map.put(timestamp, price);
    }
            
    public int current() {
        return map.containsKey(cur) ? map.get(cur) : -1;
    }
            
    public int maximum() {
        return tmap.isEmpty() ? -1 : tmap.lastKey();
    }
            
    public int minimum() {
        return tmap.isEmpty() ? -1 : tmap.firstKey();
    }
}

