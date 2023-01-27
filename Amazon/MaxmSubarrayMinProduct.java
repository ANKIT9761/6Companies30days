// link- https://leetcode.com/problems/maximum-subarray-min-product/

class Solution {
    public int maxSumMinProduct(int[] nums) {
        int n=nums.length;
        long[] presum=new long[1+n];
        long mod=(long)1e9+7;
        for(int i=1;i<=n;i++){
            presum[i]=(nums[i-1]+presum[i-1]);
            // System.out.println(presum[i]);
        }
        
        Stack<Integer> stack=new Stack<>();
        long max=-1L;
        for(int i=0;i<n+1;i++){
            while(!stack.isEmpty() && (i==n||nums[i]<=nums[stack.peek()] )){
                int indexmin=stack.pop();
                int r=i;
                int l=stack.isEmpty()? -1:stack.peek();
                l++;
                long val=presum[r]-presum[l];
                
                max=Math.max(max,(nums[indexmin]*val));
            }
            if(i<n){
                stack.push(i);
            }
        }
        return (int)(max%mod);

        
    }
}
