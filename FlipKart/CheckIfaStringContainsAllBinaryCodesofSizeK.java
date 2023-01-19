// link- https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/

class Solution {
    public boolean hasAllCodes(String s, int k) {
        HashSet<String> hs  = new HashSet<>();
        for(int i = 0;i <= s.length() - k; i++)
                hs.add(s.substring(i,i+k));
        return hs.size() == (int)Math.pow(2,k);
    }
}
