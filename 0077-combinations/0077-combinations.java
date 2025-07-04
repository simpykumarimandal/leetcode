import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> allCombinations = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>();
        backtrack(n, k, 1, currentCombination, allCombinations);
        return allCombinations;
    }

    private void backtrack(int n, int k, int start, List<Integer> currentCombination, List<List<Integer>> allCombinations) {
      
        if (currentCombination.size() == k) {
            allCombinations.add(new ArrayList<>(currentCombination)); 
            return;
        }


        if (currentCombination.size() + (n - start + 1) < k) {
            return;
        }


     
        for (int i = start; i <= n; i++) {
            currentCombination.add(i); 
            backtrack(n, k, i + 1, currentCombination, allCombinations);
            currentCombination.remove(currentCombination.size() - 1); 
        }
    }
}