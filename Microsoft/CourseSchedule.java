// link- https://leetcode.com/problems/course-schedule/

// 1st: topological sort approach.

class Solution {
   public boolean canFinish(int numCourses, int[][] prerequisites) {
            if(numCourses == 0 || prerequisites == null || prerequisites.length == 0) return true; //??
    
    // create the array lists to represent the courses
    List<List<Integer>> courses = new ArrayList<List<Integer>>(numCourses);
    for(int i=0; i<numCourses; i++) {
        courses.add(new ArrayList<Integer>());
    }
    
    // create the dependency graph
    for(int i=0; i<prerequisites.length; i++) {
        courses.get(prerequisites[i][1]).add(prerequisites[i][0]);
    }

    int[] visited = new int[numCourses]; 
    
    // dfs visit each course
    for(int i=0; i<numCourses; i++) {
           if (!dfs(i,courses, visited)) return false; 
    }
    
    return true;
}

private boolean dfs(int course, List<List<Integer>> courses, int[] visited) {
    
    visited[course] = 1; // mark it being visited
    
    List<Integer> eligibleCourses = courses.get(course); // get its children
    
    // dfs its children
    for(int i=0; i<eligibleCourses.size(); i++) {
        int eligibleCourse = eligibleCourses.get(i).intValue();
        
        if(visited[eligibleCourse] == 1) return false; // has been visited while visiting its children - cycle !!!!
        if(visited[eligibleCourse]  == 0) { // not visited
           if (!dfs(eligibleCourse,courses, visited)) return false; 
        }

    }
    
    visited[course] = 2; // mark it done visiting
    return true;
    
}
    }


// 2nd:Khan bfs Approach

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int [] inDegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        int count= 0;
        for(int i = 0; i < prerequisites.length; i++)
        inDegree[prerequisites[i][0]]++;
        for(int i = 0; i < numCourses;i++)
        {
            if(inDegree[i] == 0)
            queue.add(i);
        }
        while(queue.size() > 0)
        {
            int node = queue.poll();
            count++;

            for(int j = 0; j < prerequisites.length;j++)
            {
                if(node == prerequisites[j][1])
                {
                    inDegree[prerequisites[j][0]]--;
                    if(inDegree[prerequisites[j][0]] == 0)
                    queue.add(prerequisites[j][0]);
                }
            }
        }
        return count == numCourses;
    }
}
