class Solution {
    public int[] productQueries(int n, int[][] queries) {
        int MOD = (int) (1e9 + 7);    
        int q = queries.length;   
        List<Integer> powers = new ArrayList<>(); 
        for (int i = 0; i < 31; i++) {         
            if ((n >> i & 1) == 1)         

                powers.add(1 << i);         
        }

        int[] ans = new int[q];           

        for (int i = 0; i < q; i++) {            
            int left = queries[i][0];     
            int right = queries[i][1];     
            long product = 1;          

            for (int j = left; j <= right; j++) 
                product = (product * powers.get(j)) % MOD; 

            ans[i] = (int) product;   
        }

        return ans;      
    }
}