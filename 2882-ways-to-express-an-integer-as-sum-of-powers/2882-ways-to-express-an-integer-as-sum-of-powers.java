class Solution {
    static final int M = 1000000007;
    int[][] memo = new int[301][301];

    public Solution() {
        for (int i = 0; i < 301; i++) {
            for (int j = 0; j < 301; j++) {
                memo[i][j] = -1;
            }
        }
    }

    private int func(int n, int sum, int x, int num) {
        if (sum == n) { return 1; }
        int tmp = (int) Math.pow(num, x);

        if (sum + tmp > n) { return 0; }

        if (memo[num][sum] != -1) { return memo[num][sum]; } 

        int take = func(n, sum + tmp, x, num + 1);
        int notTake = func(n, sum, x, num + 1);

        return memo[num][sum] = (int) ((take + (long) notTake) % M); 
    }

    public int numberOfWays(int n, int x) {
        return func(n, 0, x, 1);
    }
}