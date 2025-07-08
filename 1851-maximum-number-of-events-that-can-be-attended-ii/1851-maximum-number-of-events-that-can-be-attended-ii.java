import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));

        int n = events.length;
        int[][] dp = new int[n + 1][k + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= k; j++) {
                int nextEventIndex = findNextEvent(events, events[i][1]);
                dp[i][j] = Math.max(dp[i + 1][j], events[i][2] + dp[nextEventIndex][j - 1]);
            }
        }
        return dp[0][k];
    }

    private int findNextEvent(int[][] events, int lastEndDay) {
        int low = 0;
        int high = events.length - 1;
        int result = events.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (events[mid][0] > lastEndDay) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
}