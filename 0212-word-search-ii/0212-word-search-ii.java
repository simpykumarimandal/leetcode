import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    class TrieNode {
        TrieNode[] children;
        String word; 

        public TrieNode() {
            children = new TrieNode[26];
            word = null;
        }
    }

    TrieNode root;
    Set<String> result;

    public List<String> findWords(char[][] board, String[] words) {
        root = new TrieNode();
        result = new HashSet<>();

      
        for (String word : words) {
            insertWord(word);
        }

        int m = board.length;
        int n = board[0].length;

    
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root);
            }
        }

        return new ArrayList<>(result);
    }

    private void insertWord(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.word = word; 
    }

    private void dfs(char[][] board, int row, int col, TrieNode node) {
    
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }

        char currentChar = board[row][col];
        int index = currentChar - 'a';


        if (currentChar == '#' || node.children[index] == null) {
            return;
        }

        TrieNode nextNode = node.children[index];

      
        if (nextNode.word != null) {
            result.add(nextNode.word);
 
            nextNode.word = null; 
        }

      
        board[row][col] = '#';

       
        dfs(board, row + 1, col, nextNode); 
        dfs(board, row - 1, col, nextNode); 
        dfs(board, row, col + 1, nextNode); 
        dfs(board, row, col - 1, nextNode); 
        board[row][col] = currentChar;
    }
}