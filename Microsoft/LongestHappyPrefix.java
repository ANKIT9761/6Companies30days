// link- https://leetcode.com/problems/longest-happy-prefix/

// kmp algo simple lps finding 

class Solution {
    public String longestPrefix(String s) {
        int j=0;
        int i=1;
        int prefix[]=new int[s.length()];
        while(i<s.length()){
            if(s.charAt(i)==s.charAt(j)){
                prefix[i]=j+1;
                i++;
                j++;
            }
            else{
                if(j==0){
                    prefix[i]=0;
                    i++;
                }
                else{
                    j=prefix[j-1];
                }
            }
        }
        return s.substring(0,prefix[s.length()-1]);
    }
}
