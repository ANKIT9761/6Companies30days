// link- https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/

class Solution {
    public int numberOfSubstrings(String s) {
        int count = 0;
        int[] map = new int[3];
        int left = 0;
        for(int right = 0;right < s.length() ; right++){
            map[s.charAt(right) - 'a']++;
            while(map[0] > 0 && map[1] > 0 && map[2] > 0){
                map[s.charAt(left++) - 'a']--;
            }
           
            count+= left;
        }
        return count;
    }
}
