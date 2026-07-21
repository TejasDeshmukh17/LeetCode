import java.util.*;

class Solution {
    public int totalFruit(int[] fruits) {
        int left = 0;
        int maxFruits = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int right = 0; right < fruits.length; right++) {
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);

            // If more than 2 types of fruits, shrink the window
            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left]) - 1);

                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }

                left++;
            }

            // Update maximum fruits collected
            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }
}