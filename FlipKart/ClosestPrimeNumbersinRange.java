// link- https://leetcode.com/problems/closest-prime-numbers-in-range/

// normal solution

class Solution {
    public int[] closestPrimes(int left, int right) {
        
        int[] nums = {-1, -1};
        int min = Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<>();

        for (int i = left; i <= right; i++){
            if (isPrime(i)){
                list.add(i);
            }
        }

        if (list.isEmpty() || list.size() < 2){
            return nums;
        }

        for (int i = 1; i < list.size(); i++){
            int temp = list.get(i) - list.get(i-1);
            if (min > temp){
                min = temp;
                nums[0] = list.get(i-1);
                nums[1] = list.get(i);
            }
        }

        return nums;
    }

    public boolean isPrime(int num){

        int n = (int) Math.sqrt(num);

        for (int i = 2; i <= n; i++){
            if (num % i == 0){
                return false;
            }
        }

        return num != 1;
    }
}

// sieve of Eratosthenes solution

class Solution {
    int[] isPrime(int l, int n) {
        ArrayList<Integer> v = new ArrayList<>();
        boolean[] prime = new boolean[n + 1];
        int[] arr = new int[2];
        for (int i = 2; i * i <= n; i++) {
            if (prime[i] == false) {
                for (int j = i * i; j <= n; j = j + i) {
                    prime[j] = true;
                }
            }
        }
        
        for (int i = l; i <= n; i++) {
            if (prime[i] == false) {
                v.add(i);
            }
        }
        if (l<=1) {
            v.remove(0);
        }
        arr[0]=-1;
        arr[1]=-1;
        int min = Integer.MAX_VALUE;
        int j=-1;
        for (int i=0;i<=v.size()-1;i++) {
            if (j==-1) {
                j=i;
            } else if (v.get(i)-v.get(j)<min) {
                arr[0]=v.get(j);
                arr[1]=v.get(i);
                min=v.get(i)-v.get(j);
            }
            j=i;
        }
        return arr;
    }
    public int[] closestPrimes(int left, int right) {
        int[] ans=isPrime(left,right);
        return ans;
    }
}
