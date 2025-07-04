import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private Map<Character, String> mapping;
    private List<String> result;

    public List<String> letterCombinations(String digits) {
        result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        mapping = new HashMap<>();
        mapping.put('2', "abc");
        mapping.put('3', "def");
        mapping.put('4', "ghi");
        mapping.put('5', "jkl");
        mapping.put('6', "mno");
        mapping.put('7', "pqrs");
        mapping.put('8', "tuv");
        mapping.put('9', "wxyz");

        backtrack(digits, 0, new StringBuilder());

        return result;
    }

    private void backtrack(String digits, int index, StringBuilder currentCombination) {
        
        if (index == digits.length()) {
            result.add(currentCombination.toString());
            return;
        }

        char digit = digits.charAt(index);
        String letters = mapping.get(digit);

     
        for (char letter : letters.toCharArray()) {
            currentCombination.append(letter); 
            backtrack(digits, index + 1, currentCombination); 
            currentCombination.deleteCharAt(currentCombination.length() - 1); 
        }
    }
}