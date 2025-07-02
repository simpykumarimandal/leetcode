import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] inDegree = new int[numCourses];

        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prerequisite = prereq[1];
            adj.computeIfAbsent(prerequisite, k -> new ArrayList<>()).add(course);
            inDegree[course]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        int[] result = new int[numCourses];
        int index = 0;

        while (!q.isEmpty()) {
            int course = q.poll();
            result[index++] = course;

            if (adj.containsKey(course)) {
                for (int neighbor : adj.get(course)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) {
                        q.offer(neighbor);
                    }
                }
            }
        }

        if (index == numCourses) {
            return result;
        } else {
            return new int[0]; 
        }
    }
}