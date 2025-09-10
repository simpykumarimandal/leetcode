class Solution {
    int mod = (int)(1e9 + 7);

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long[] dp = new long[n + 1]; 
        dp[1] = 1;

        long noOfPeopleWithSecret = 0; 

        for (int i = 2; i <= n; i++) {
            
            long noOfNewPeopleWithSecret = 0;
            if (i - delay >= 1)
                noOfNewPeopleWithSecret = dp[i - delay];

            
            long noOfPeopleForgetSecret = 0;
            if (i - forget >= 1)
                noOfPeopleForgetSecret = dp[i - forget];

            
            noOfPeopleWithSecret = (noOfPeopleWithSecret + noOfNewPeopleWithSecret - noOfPeopleForgetSecret + mod) % mod;

         
            dp[i] = noOfPeopleWithSecret;
        }

        long ans = 0;
        for (int i = n - forget + 1; i <= n; i++) {
            if (i >= 1) ans = (ans + dp[i]) % mod;
        }
        return (int) ans;
    }
}