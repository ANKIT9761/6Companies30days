// link- https://leetcode.com/problems/minimum-genetic-mutation/

class Solution {
    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) return 0;

        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(end)) return -1;

        char[] charSet = new char[]{'A', 'C', 'G', 'T'};
        int level = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(end)) return level;
                char[] currArray = curr.toCharArray();
                for (int j = 0; j < currArray.length; j++) {
                    char originalChar = currArray[j];
                    for (char c : charSet) {
                        currArray[j] = c;
                        String next = new String(currArray);
                        if (!visited.contains(next) && bankSet.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                    currArray[j] = originalChar;
                }
            }
            level++;
        }
        return -1;
    }
}
