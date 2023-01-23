// link- https://leetcode.com/problems/random-pick-with-weight/

class Solution {

  TreeMap<Integer, Integer> map;
  int sum;

  public Solution(int[] w) {
    map = new TreeMap<>();
    sum = 0;
    for (int i = 0; i < w.length; i++) {
      sum += w[i];
      map.put(sum, i);
    }
  }

  public int pickIndex() {
    int random = new Random().nextInt(sum) + 1; // 1 to sum inclusive
    return map.ceilingEntry(random).getValue();
  }
}
