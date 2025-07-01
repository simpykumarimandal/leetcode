import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        int[] inDegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            adj.get(prerequisite[1]).add(prerequisite[0]);
            inDegree[prerequisite[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        int coursesTaken = 0;
        while (!q.isEmpty()) {
            int course = q.poll();
            coursesTaken++;

            for (int neighbor : adj.get(course)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        return coursesTaken == numCourses;
    }
}