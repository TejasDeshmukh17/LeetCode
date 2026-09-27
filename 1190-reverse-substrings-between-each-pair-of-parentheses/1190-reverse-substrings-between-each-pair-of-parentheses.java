import java.util.*;

class Solution {
    public String reverseParentheses(String s) {

        Stack<String> stack = new Stack<>();
        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // Save current string
                stack.push(current.toString());

                // Start a new substring
                current.setLength(0);

            } 
            else if (ch == ')') {
                // Reverse current substring
                current.reverse();

                // Get previous string
                String previous = stack.pop();

                // Append reversed substring
                current.insert(0, previous);
            } 
            else {
                // Normal character
                current.append(ch);
            }
        }

        return current.toString();
    }
}