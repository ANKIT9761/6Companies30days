// link- https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/

// This solution was taken with the help of chatgpt man this is better than any discussion section answers.

/*

The approach of the solution is to make use of the fact that all the digits of the number is at most n. It starts with the number 1,
 and it counts how many numbers are there starting with the current number, and how many numbers are there starting with the next number, 
and choose the one that has more numbers, to minimize the steps it takes to reach the Kth smallest number. It keeps track of the current number
 and the kth position number.

It use a function countSteps to count the steps between curr and next, by using while loop, It keeps track of the number of steps it takes to reach 
the next number and it compares it with the number of steps it takes to reach the next number. If the steps are less than or equal to k, it increments
 the current number and subtracts the steps from k, otherwise, it multiplies the current number by 10 and decrements k.

This solution has a time complexity of O(log n) because we are traversing the tree in a balanced manner and the space complexity is O(1) as we are not 
using any additional data structures to store the values.

*/

class Solution {
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while (k > 0) {
            int steps = countSteps(n, curr, curr + 1);
            if (steps <= k) {
                curr += 1;
                k -= steps;
            } else {
                curr *= 10;
                k--;
            }
        }
        return curr;
    }

    // Count the steps between curr and next
    private int countSteps(int n, long curr, long next) {
        int steps = 0;
        while (curr <= n) {
            steps += Math.min(n + 1, next) - curr;
            curr *= 10;
            next *= 10;
        }
        return steps;
    }
}
