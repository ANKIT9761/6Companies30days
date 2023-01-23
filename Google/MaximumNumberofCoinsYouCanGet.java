// link- https://leetcode.com/problems/maximum-number-of-coins-you-can-get/

class Solution {
    public int maxCoins(int[] piles) {
      int times = piles.length/3;
      int ans=0;
      Arrays.sort(piles);
      for(int i=piles.length-2;i>0;i=i-2){
          ans=ans+piles[i];
          times--;
          if(times==0) return ans;
      }
      return ans;  
    }
}
