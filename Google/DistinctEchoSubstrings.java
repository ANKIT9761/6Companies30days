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

class Solution {
    long hash[];
    long pow[];
    long r=256;
    long mod=(long)Math.pow(10,9)+7;
    public void process(String s,int n)
    {
        hash=new long[n];
        pow=new long[n];
        pow[0]=1;
        for(int i=1;i<n;i++)
        {
            hash[i]=(hash[i-1]*r+s.charAt(i))%mod;
            pow[i]=(pow[i-1]*r)%mod;
        }
    }
    public long calc(int l,int r)
    {
        long hashValue=(hash[r]-hash[l]*pow[r-l]%mod+mod)%mod;
        return hashValue;
    }
    public int distinctEchoSubstrings(String text) {
        int n=text.length();
        process(text,n);
        HashSet<Long> hset=new HashSet<>();
        for(int len=1;len<=n/2;len++)
        {
            int c=0;
            for(int l=0,r=len;r<n;l++,r++)
            {
                if(text.charAt(l)==text.charAt(r))
                {
                   c++; 
                }
                else
                {
                    c=0;
                }
                if(c==len)
                {
                    long hv=calc(l,r);
                    hset.add(hv);
                    c--;
                }
            }
        }
        return hset.size();
        
    }
}
