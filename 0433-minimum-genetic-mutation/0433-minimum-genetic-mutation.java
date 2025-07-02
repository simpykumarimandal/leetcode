import java.util.*;

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        
        if (!bankSet.contains(endGene)) {
            return -1;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);
        
        Set<String> visited = new HashSet<>();
        visited.add(startGene);
        
        int mutations = 0;
        char[] geneChars = {'A', 'C', 'G', 'T'};
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                String currentGene = queue.poll();
                
                if (currentGene.equals(endGene)) {
                    return mutations;
                }
                
                char[] currentGeneArray = currentGene.toCharArray();
                for (int j = 0; j < currentGeneArray.length; j++) {
                    char originalChar = currentGeneArray[j];
                    for (char c : geneChars) {
                        if (c == originalChar) {
                            continue;
                        }
                        currentGeneArray[j] = c;
                        String newGene = new String(currentGeneArray);
                        
                        if (bankSet.contains(newGene) && !visited.contains(newGene)) {
                            queue.offer(newGene);
                            visited.add(newGene);
                        }
                    }
                    currentGeneArray[j] = originalChar; 
                }
            }
            mutations++;
        }
        
        return -1;
    }
}