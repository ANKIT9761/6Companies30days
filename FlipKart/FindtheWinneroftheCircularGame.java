// link- https://leetcode.com/problems/find-the-winner-of-the-circular-game/

class Solution {
    public int ans(int []arr, int n, int k, int counter){
        if(n==1){
            return arr[0];
        }
        for(int i=counter+1;i<n;++i){
            arr[i-1] = arr[i];
        }
        n--;

        return ans(arr,n,k,(counter+(k-1))%n);
    }
    
    public int findTheWinner(int n, int k) {
        int []arr = new int[n];

        for(int i=0;i<n;++i){
            arr[i] = i+1;
        }
        return ans(arr,n,k,(k-1)%n);
    }
}

// linear time approach

class Solution {
    public int findTheWinner(int n, int k) {
        int i=1, ans=0;

        while(i<=n){
            ans = (ans+k)%i;
            i++;
        }

        return ans+1;
    }
}
