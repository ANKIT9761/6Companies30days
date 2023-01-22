// link- https://leetcode.com/problems/sort-an-array/

class Solution {
    public int[] sortArray(int[] nums) {
        sort(nums,0,nums.length-1);
        return nums;
    }

    void merge(int arr[], int l, int m, int r)
    {
        
        int n1 = m - l + 1;
        int n2 = r - m;
 
       
        int L[] = new int[n1];
        int R[] = new int[n2];
 
        
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
 
       
        int i = 0, j = 0;
 
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
       
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
 
      
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
    public void sort(int [] nums,int left,int right){
        if(left<right){
            int mid=left+(right-left)/2;
            sort(nums,left,mid);
            sort(nums,mid+1,right);
            merge(nums,left,mid,right);
        }
    }
}
