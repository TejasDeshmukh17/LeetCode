class Solution {
    public String minWindow(String s, String t) {
        if(s.length()<t.length())
        {
            return "";
        }

        Map <Character , Integer> countT = new HashMap<>();

        for(char c : t.toCharArray())
        {
            countT.put(c,countT.getOrDefault(c,0)+1);
        }

        Map<Character,Integer> window = new HashMap<>();
        int left = 0;
        int have = 0;
        int minLeft = 0;
        int need =  countT.size();
        int minLength = Integer.MAX_VALUE;

        for(int right = 0; right<s.length();right++)
        {
            char c = s.charAt(right);

             window.put(c,window.getOrDefault(c,0)+1);

             if(countT.containsKey(c) && window.get(c).intValue() == countT.get(c).intValue())
             {
                have++;
             }

             while(have == need)
             {
                if ( right - left + 1 < minLength)
                {
                    minLength = right - left + 1;
                    minLeft = left;
                }

                char leftChar = s.charAt(left);

                window.put(
                    leftChar,
                    window.get(leftChar) - 1
                );

                if(countT.containsKey(leftChar)  && window.get(leftChar)< countT.get(leftChar))
                       {
                        have--;
                       }

                       left++;
             }
        }

        if(minLength == Integer.MAX_VALUE)
        {
            return "";
        }

        return s.substring(minLeft ,  minLength + minLeft);



    }
}