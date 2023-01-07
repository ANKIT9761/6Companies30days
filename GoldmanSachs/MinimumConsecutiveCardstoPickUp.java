// link- https://leetcode.com/problems/minimum-consecutive-cards-to-pick-up/

class Solution {
    public int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> map= new HashMap<>();

        int res= Integer.MAX_VALUE;
        for(int i=0;i<cards.length;i++){
            if(map.containsKey(cards[i])){
                res= Math.min(res, i- map.get(cards[i])+1);
            }   
            map.put(cards[i], i);
        }

        return res==Integer.MAX_VALUE?-1:res;
    }
}
