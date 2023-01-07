// link- https://leetcode.com/problems/number-of-boomerangs/

//T.C = O(n^2)
//S.C = O(n)

class Solution {
    public int numberOfBoomerangs(int[][] arr) {
        
        /*The idea here is to create a hashmap that stores the distace
        of each point from one another along with their frequencies. 
        From the frequencies we calculate the the no. of boomerangs.
        We store the squares of distances as each number has a unique 
        square and non-perfect square distances doesn't cause problems
        while recording frequencies.
        */
        int res = 0;
        
        if(arr.length == 1) return 0;
        
        for (int i = 0; i < arr.length; i++){
            
             HashMap<Integer, Integer> map = new HashMap<>();
            
            for (int j = 0; j < arr.length; j++){
                
                if(i == j) continue;
                
                int dist = distanceSquare(arr[i], arr[j]);
                
                map.put(dist, map.getOrDefault(dist, 0)+1);
        
            }
            
            for (int n : map.values()){
                res = res + n * (n-1); 
            }
            /*This is the most crucial step as it gives us the ways the 
            boomerangs can be arranged with each other. Suppose there are 
            'n' distances that are equal to each other and are stored 
            as the values of the hashmap. If we select any one out of 
            them we are left with (n-1) distances and the 'n' previous
            distances can be rearranged in (n-1) ways to form a total 
            of n*(n-1) boomerangs.
            */
        }
        return res;
    }
    
    public int distanceSquare(int[] a, int[] b){
        return  (b[0] - a[0])*(b[0] - a[0]) + (b[1] - a[1])*(b[1] - a[1]); 
        //This basically is the square of distance formula between any two points.
    }
}
