// link- https://leetcode.com/problems/minimum-deletions-to-make-array-divisible/

class Solution {
    public int minOperations(int[] nums, int[] numsDivide) {
        Arrays.sort(nums);
        Arrays.sort(numsDivide);
     
        int hcf=findgcd(numsDivide,numsDivide.length);
        
        int count=0;
        for(int i:nums){
            
            if(hcf%i==0) return count;
            count++;
        }
        return -1;
    }
    public int calcHcf(int x,int y){
        if (y == 0)
            return x;
        return calcHcf(y, x % y);
    }

    public  int findgcd(int[] arr,int n){
        int f=arr[0];
        for(int e:arr){
            f=calcHcf(e,f);

            if(f==1){
                return 1;
            }
        }
        return f;
    }
}
