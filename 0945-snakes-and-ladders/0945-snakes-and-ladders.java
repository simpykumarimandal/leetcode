import java.util.*;

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int target = n * n;
        
        Map<Integer, Integer> dist = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        
        dist.put(1, 0);
        q.offer(1);
        
        while (!q.isEmpty()) {
            int curr = q.poll();
            
            if (curr == target) {
                return dist.get(curr);
            }
            
            for (int i = 1; i <= 6; i++) {
                int next = curr + i;
                if (next > target) {
                    continue;
                }
                
                int[] rc = getCoordinates(next, n);
                int r = rc[0];
                int c = rc[1];
                
                int destination = next;
                if (board[r][c] != -1) {
                    destination = board[r][c];
                }
                
                if (!dist.containsKey(destination)) {
                    dist.put(destination, dist.get(curr) + 1);
                    q.offer(destination);
                }
            }
        }
        
        return -1;
    }
    
    private int[] getCoordinates(int square, int n) {
        int r = (square - 1) / n;
        int c = (square - 1) % n;
        
        int row = n - 1 - r;
        int col;
        if (r % 2 == 0) { 
            col = c;
        } else { 
            col = n - 1 - c;
        }
        return new int[]{row, col};
    }
}