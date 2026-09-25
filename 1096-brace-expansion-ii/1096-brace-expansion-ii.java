import java.util.*;

class Solution {

    private String s;
    private int index;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        index = 0;

        Set<String> result = parse();

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse() {

        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        while (index < s.length() && s.charAt(index) != '}') {

            char ch = s.charAt(index);

                    if (ch == ',') {
                result.addAll(current);
                current = new HashSet<>();
                current.add("");
                index++;
            }

            
            else if (ch == '{') {
                index++; 

                Set<String> inside = parse();

                index++; 

                current = concatenate(current, inside);
            }

            else {
                Set<String> letter = new HashSet<>();
                letter.add(String.valueOf(ch));

                current = concatenate(current, letter);

                index++;
            }
        }

        result.addAll(current);

        return result;
    }

    private Set<String> concatenate(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}