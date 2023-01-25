// link- https://leetcode.com/problems/maximum-compatibility-score-sum/

class Solution {
	int max;
	public int maxCompatibilitySum(int[][] students, int[][] mentors) {
		boolean[] visited = new boolean[students.length];
		helper(visited, students, mentors, 0, 0);
		return max;
	}
	public void helper(boolean[] visited, int[][] students, int[][] mentors, int pos, int score){
		if(pos >= students.length){
			max = Math.max(max, score);
			return;
		}
		for(int i = 0; i < mentors.length; i++)
			if(!visited[i]){
				visited[i] = true;
				helper(visited, students, mentors, pos + 1, score + score(students[pos], mentors[i]));
				visited[i] = false;
			}
	}
	public int score(int[] a, int[] b){
		int count = 0;

		for(int i = 0; i < b.length; i++)
			if(a[i] == b[i]) count += 1;
		return count;
	}
}

// 2nd aprroach 

/*

The solution uses a depth-first search (DFS) algorithm with memoization to find the maximum compatibility score sum.
 The maxCompatibilitySum method is the entry point of the algorithm, and it calls the private dfs method, passing in 
the arrays of students and mentors, the current index, the current status, and a memoization array.

The dfs method is responsible for recursively traversing through all possible combinations of students and mentors, and
 for each combination, it calls the comp method to calculate the compatibility score. If a combination has already been visited,
 the method retrieves the result from the memoization array instead of recalculating it. The method uses bit manipulation to keep
 track of the mentors that have already been used and the memoization array to store the result for each subproblem.

The calculateCompatibility method compares the interests of a student and a mentor and returns the number of matching interests, which is the 
compatibility score.

The solution uses memoization to avoid recalculating the same subproblems and bit manipulation to keep track of the mentors 
that have already been used, which reduces the time complexity of the algorithm.

*/

class Solution {
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        return dfs(students, mentors, 0, 0, new Integer[students.length][256]);
    }

    private int dfs(int[][] students, int[][] mentors, int studentIndex, int mentorStatus, Integer[][] memo) {
        if (studentIndex == students.length) {
            return 0;
        }
        if (memo[studentIndex][mentorStatus] != null) {
            return memo[studentIndex][mentorStatus];
        }

        int maxCompatibilitySum = 0;
        for (int mentorIndex = 0; mentorIndex < mentors.length; mentorIndex++) {
            if ((mentorStatus & (1 << mentorIndex)) == 0) {
                int compatibility = calculateCompatibility(students[studentIndex], mentors[mentorIndex]);
                int nextMentorStatus = mentorStatus | (1 << mentorIndex);
                int subProblemMaxCompatibilitySum = dfs(students, mentors, studentIndex + 1, nextMentorStatus, memo);
                maxCompatibilitySum = Math.max(maxCompatibilitySum, compatibility + subProblemMaxCompatibilitySum);
            }
        }
        memo[studentIndex][mentorStatus] = maxCompatibilitySum;
        return maxCompatibilitySum;
    }

    private int calculateCompatibility(int[] student, int[] mentor) {
        int compatibility = 0;
        for (int i = 0; i < student.length; i++) {
            if (student[i] == mentor[i]) {
                compatibility++;
            }
        }
        return compatibility;
    }
}
