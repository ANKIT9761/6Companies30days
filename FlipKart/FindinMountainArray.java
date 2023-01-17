// link- https://leetcode.com/problems/find-in-mountain-array/

class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        
        // get index of peak element to differentiate ascending and descending part of the array
        int peakElementIndex = findPeakElementIndex(mountainArr);
        
        // search in ascending array(including peak element)
        int targetIndex = binarySearchTarget(mountainArr, target, 0, peakElementIndex, true);
        
        // search in descending array if element not found in ascending part of the array
        if(targetIndex == -1){
            targetIndex = binarySearchTarget(mountainArr, target, peakElementIndex + 1, mountainArr.length() - 1, false);
        }
        
        return targetIndex;
    }
    

    public int findPeakElementIndex(MountainArray mountainArr){
        /*
            *   returns index of peak element
            *   peak element: one that is greater then element before it, as well as the element after it
            *   did not considered elementAtMid == elementNextToMid, as there are no duplicate elements present
        */
        
        int start = 0, end = mountainArr.length() - 1;
        
        while(start < end){
            
            // using below optimised formula to stay within the integer limits
            int mid = start + (end - start)/2;
            
            // implemented to reduce the number of mountainArr.get() calls 
            int elementAtMid = mountainArr.get(mid);
            int elementNextToMid = mountainArr.get(mid+1);
            
            if(elementAtMid > elementNextToMid){
                end = mid;
            }
            else if(elementAtMid < elementNextToMid){
                start = mid + 1;
            }
            
        }
        return end;
        
    }
    
    public int binarySearchTarget(MountainArray mountainArr, int target, int start, int end, boolean isAsc){
        
        /*
            *   returns index of target element if found, else -1
            *   boolean isAsc: true if searching in ascending part of the array, false for descending part of the array
        */
        
        while(start <= end){
            int mid = start + (end - start)/2;
            
            int midIndex = mountainArr.get(mid);
            
            if(midIndex == target){
                return mid;
            }
            else if(midIndex > target){
                if(isAsc){
                    end = mid - 1;
                }
                else{
                    start = mid + 1;
                }
                    
            }
            else if(midIndex < target){
                if(isAsc){
                    start = mid + 1;
                }
                else{
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
    
}
