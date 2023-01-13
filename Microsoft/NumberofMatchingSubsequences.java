// link- https://leetcode.com/problems/number-of-matching-subsequences/

class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        int count = 0;
        Map<String, Integer> map = new HashMap<>();
        for(String word: words){
            map.put(word, map.getOrDefault(word, 0)+1);
        }
        for(String word: map.keySet()){
            int j=0, k =0;
            while(j<word.length() && k<s.length()){
                if(word.charAt(j) == s.charAt(k)){
                    j++;
                }
                k++;
            }
          
            if(j == word.length()){
                count+= map.get(word);
            }
                
        }
        return count;
    }
}
