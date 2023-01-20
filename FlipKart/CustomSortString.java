// link- https://leetcode.com/problems/custom-sort-string/

// Hashmap Approach

class Solution {
    public String customSortString(String order, String s) {
        StringBuffer ans = new StringBuffer("");
        Map<Character, Integer> freq = new HashMap<>();
        for(char c: s.toCharArray())
            freq.put(c, freq.getOrDefault(c, 0)+1);
        for(char c: order.toCharArray()) {
            int n = freq.getOrDefault(c, 0);
            for(int i=0;i<n;i++)
                ans.append(c);
            freq.put(c, 0);
        }
        for(char c: freq.keySet())
            for(int i=0;i<freq.get(c);i++)
                ans.append(c);
        return ans.toString();
    }
}

// Custom Sorting Approach 

class Solution {
    class MyCmp implements Comparator<Character>{
        String order;
        MyCmp(String order){this.order=order;};
        public int compare(Character s1, Character s2){
            return order.indexOf(s1)-order.indexOf(s2);
        }
    }
    public String customSortString(String order, String s) {

        // converting string to char array;
        char car[] = s.toCharArray();

        // converting char to Wrapper class Character
        Character arr[] = new Character[car.length];
        for(int i=0; i<car.length; i++){
            arr[i]=car[i];
        }

        // Sorting the array using custom comparator
        Arrays.sort(arr,new MyCmp(order));

        // converting array to string
        StringBuilder res = new StringBuilder();
        for(int i=0; i<car.length; i++){
            res.append(arr[i]);
        }

        return res.toString();
    }
}
