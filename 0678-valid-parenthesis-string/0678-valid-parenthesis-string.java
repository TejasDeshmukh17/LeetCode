class Solution {
    public boolean checkValidString(String s) {
        int left= 0;
        int right = 0;

        for(char c : s.toCharArray())
        {
            if(c == '(')
            {
                left++;
                right++;
            }

            else if ( c == ')')
            {
                left--;
                right--;
            }
            else{
                left--;
                right++;
            }

            if(right < 0)
            {
                return false;
            }
            left = Math.max(left , 0);
        }
        return left== 0;
    }
}