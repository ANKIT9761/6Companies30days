// link- https://leetcode.com/problems/query-kth-smallest-trimmed-number/

// java priority queue approach

class Solution {
    public class Pair{
        String s;
        int ind;
        Pair(String s, int i){
            this.s = s;
            this.ind = i;
        }
    }

    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        int l = 0;
    
        //Do String Comparison if same then sort on max ind else max String
        PriorityQueue<Pair> q = new PriorityQueue<>((a,b)-> (b.s.compareTo(a.s) == 0) ? b.ind - a.ind 
                                                       : b.s.compareTo(a.s));
        for(int[] query : queries){
            int trim = query[1], kth = query[0];
            int len = nums[0].length();
        
            for(int i = 0; i < nums.length; i++){
                String t = nums[i].substring(len - trim);
                q.add(new Pair(t, i));
            
                //if exceeds the k size then pop the max one so we still have min
                if(q.size() > kth)
                    q.poll();
            }
        
            ans[l++] = q.peek().ind;
            q.clear();// make sure to clear the q 
        }
        return ans;
    }
}

// Radix Sort Approach

// helper link- https://leetcode.com/problems/query-kth-smallest-trimmed-number/discuss/3041025/Easy-100-Fast-Java-Solution-Using-Radix-Sort

class Solution {
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums.length;
        int len = nums[0].length();
        int[][] list = new int[len][n];
        RadixSort.radixSort(list, nums);
        int[] ans = new int[queries.length];
        int i = 0;
        for(int[] query:queries){
            ans[i++] = list[query[1]-1][query[0]-1];
        }
        return ans;
    }
}
class RadixSort{
    public static void radixSort(int[][] list, String[] nums){
        int n = nums[0].length();
        int[] index = new int[nums.length];
        for(int i=0; i<nums.length; i++)  
            index[i] = i;
        list[0] = countingSort(index, nums, n-1);
        for(int i=1; i<n; i++){
            list[i] = countingSort(list[i-1], nums, n-1-i);
        }
    }
    public static int[] countingSort(int[] index, String[] nums, int idx){
        int n = nums.length;
        int[] sorted = new int[n];
        int[] count = new int[10];
        for(String s:nums)
            count[s.charAt(idx)-'0']++;
        for(int i=1; i<10; i++)
            count[i] += count[i-1];
        for(int i=n-1; i>=0; i--){
            sorted[count[nums[index[i]].charAt(idx)-'0']-- -1] = index[i];
            // count[nums[index[i]].charAt(idx)-'0']--;
        }
        return sorted;
    }
}
