class Solution {
    public boolean isAnagram(String s, String t) {
       if(t.length() != s.length())
       {
        return false;
       } 

       HashMap<Character,Integer> count = new HashMap<>();

       for(char c : s.toCharArray())
       {
        count.put(c,count.getOrDefault(c,0)+1);
       }
       for(char c : t.toCharArray())
       {
        count.put(c,count.getOrDefault(c,0)-1);
       }

       for(int values : count.values())
       {
        if(values != 0)
        {
            return false;
        }
       
       }
        return true;
    }
}