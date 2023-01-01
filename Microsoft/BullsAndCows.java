// link - https://leetcode.com/problems/bulls-and-cows/

class Solution {
    public String getHint(String secret, String guess) {
        int[] s = new int[26];
        int[] g = new int[26];
        int bulls = 0;
        for(int i=0;i<secret.length();i++){
            s[secret.charAt(i)-'0']++;
            g[guess.charAt(i)-'0']++;
            if(secret.charAt(i) == guess.charAt(i))
                bulls++;
        }
        int cows =0;
        for(int i=0;i<26;i++){
            if(s[i] >0 && g[i]>0)
            cows+= Math.min(s[i], g[i]);
        }
        cows-=bulls;
        return bulls+"A"+cows+"B";
    }
}
