// link- https://leetcode.com/problems/distinct-echo-substrings/

// sliding window approach

class Solution {
    public int distinctEchoSubstrings(String text) {
        int ans = 0, n = text.length()/2;
        for (int i = 1; i <= n; i++){ // i = length
            Set<String> seen = new HashSet<>();
            for (int j = 0, k = j+i, ok = 0; k < text.length(); j++, k++){ // j = start of 1st part, k = start of 2nd part.
                if (text.charAt(j) == text.charAt(k)){ // matched
                    ok++;
                }
                if (j >= i && text.charAt(j-i) == text.charAt(k-i)){ // was matched, but out of bound now, so delete 1
                    ok--;
                }
                if (j >= i-1 && ok == i && seen.add(text.substring(j-i+1,j+1))){ // if it echoes and we've not seen it, add 1 to ans
                    ans++;
                }
            }
        }
        return ans;
    }
}

// Rolling hash Algo

/*

This solution uses a two-pointer approach where it starts with substring of length 1 and then iterates over all substring of that length
 and check for the first and last character of substring if they are same or not. If they are same it increases the counter, if counter 
becomes equal to the length of substring then it calculates hash value of substring and add it to the set and decrease the counter by 1.

This solution has a Time complexity of O(n^2) which is better than the previous solutions because it iterates over all the substring of length
 n/2 and also it uses a hashset which has a time complexity of O(1) for adding an element.

*/

class Solution {
    private long hash[];
    private long power[];
    private long base = 256;
    private long mod = (long)Math.pow(10,9) + 7;
    
    private void processString(String text, int textLength) {
        hash = new long[textLength];
        power = new long[textLength];
        power[0] = 1;
        for(int i = 1; i < textLength; i++) {
            hash[i] = (hash[i-1] * base + text.charAt(i)) % mod;
            power[i] = (power[i-1] * base) % mod;
        }
    }
    
    private long calculateHash(int left, int right) {
        long hashValue = (hash[right] - hash[left] * power[right - left] % mod + mod) % mod;
        return hashValue;
    }
    
    public int distinctEchoSubstrings(String text) {
        int textLength = text.length();
        processString(text, textLength);
        Set<Long> uniqueHashes = new HashSet<>();
        for(int length = 1; length <= textLength/2; length++) {
            int count = 0;
            for(int left = 0, right = length; right < textLength; left++, right++) {
                if(text.charAt(left) == text.charAt(right)) {
                    count++; 
                } else {
                    count = 0;
                }
                if(count == length) {
                    long hashValue = calculateHash(left, right);
                    uniqueHashes.add(hashValue);
                    count--;
                }
            }
        }
        return uniqueHashes.size();
    }
}
