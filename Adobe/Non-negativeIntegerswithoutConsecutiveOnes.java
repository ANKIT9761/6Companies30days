// link- https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/

// helper link- https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/discuss/1361796/Non-ve-Integers-without-Consecutive-1s-oror-Java-oror-Explaination

class Solution {
    public int findIntegers(int n) {
        String binary = Integer.toBinaryString(n);
        int k = binary.length();

        int[] fib = new int[k+1];
        fib[0] = 1;
        fib[1] = 2;
        for(int i=2;i<=k;i++){
            fib[i] = fib[i-1]+fib[i-2];
        }
        
        boolean isLastBitOne = false;
        int res=0;
        int bit = k-1;
        while(bit>=0){
            if((n & (1<<bit))==0){
                isLastBitOne=false;
            } else {
                res+=fib[bit];
                if(isLastBitOne){
                    return res;
                }
                isLastBitOne=true;
            }
            bit--;
        }
        
        return res+1; // Since we started from one lesser bit, we add one to include 'n' itself
    }
}
