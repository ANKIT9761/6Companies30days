// link- https://leetcode.com/problems/strictly-palindromic-number/

// 1st approach

class Solution {
    public boolean isStrictlyPalindromic(int n) {
        
        int check;
        for(int i=2; i<=n-2; i++){
            check = getBaseNumber(n,i);
            if(!isPalindrome(check)){
                return false;
            }
        }
        return true;
    }

    public int getBaseNumber(int n,int base){
        if(n==0){
            return 0;
        }
        int currRem = n%base;
        int currAns = getBaseNumber(n/base , base)*10 + currRem;
        return currAns;
    }
    public boolean isPalindrome(int n){
        int m=0;
        int temp=n;
        while(temp>0){
            m = m*10 + (temp%10);
            temp /= 10;
        }
        return m==n;
    }
}

