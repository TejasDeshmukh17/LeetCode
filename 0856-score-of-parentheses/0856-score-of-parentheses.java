class Solution {
    public int scoreOfParentheses(String s) {
       Stack<Integer> stack = new Stack<>();
       stack.push(0);

       for(char c : s.toCharArray())
       {
        if(c == '(')
        {
            stack.push(0);
        }
        else {
            int inScore = stack.pop();
            int score;

            if(inScore == 0)
            {
                score = 1;
            }
            else 
            {
                score = 2 * inScore;
            }

            stack.push(stack.pop()  + score);
        }

       } 

       return stack.pop();
    }
}