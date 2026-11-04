
import java.util.*;

class Solution {

    class MedianWindow {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int leftSize = 0, rightSize = 0;
        long sumLeft = 0, sumRight = 0;

        void add(int num) {
            if (leftSize == 0 || num <= left.lastKey()) {
                left.put(num, left.getOrDefault(num, 0) + 1);
                leftSize++;
                sumLeft += num;
            } else {
                right.put(num, right.getOrDefault(num, 0) + 1);
                rightSize++;
                sumRight += num;
            }

            balance();
        }

        void remove(int num) {
            if (left.containsKey(num)) {
                decrement(left, num);
                leftSize--;
                sumLeft -= num;
            } else {
                decrement(right, num);
                rightSize--;
                sumRight -= num;
            }

            balance();
        }

        void decrement(TreeMap<Integer, Integer> map, int num) {
            int count = map.get(num);

            if (count == 1) {
                map.remove(num);
            } else {
                map.put(num, count - 1);
            }
        }

        void balance() {
            while (leftSize > rightSize + 1) {
                int num = left.lastKey();

                decrement(left, num);
                leftSize--;
                sumLeft -= num;

                right.put(num, right.getOrDefault(num, 0) + 1);
                rightSize++;
                sumRight += num;
            }

            while (leftSize < rightSize) {
                int num = right.firstKey();

                decrement(right, num);
                rightSize--;
                sumRight -= num;

                left.put(num, left.getOrDefault(num, 0) + 1);
                leftSize++;
                sumLeft += num;
            }
        }

        long getCost() {
            long median = left.lastKey();

            return median * leftSize - sumLeft
                    + sumRight - median * rightSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        long[] cost = new long[n - x + 1];

        MedianWindow window = new MedianWindow();

        // Calculate minimum cost for every window
        for (int i = 0; i < n; i++) {

            window.add(nums[i]);

            if (i >= x) {
                window.remove(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = window.getCost();
            }
        }

        // DP
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (int j = 0; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int j = 1; j <= k; j++) {

            for (int i = 1; i <= n; i++) {

                dp[j][i] = dp[j][i - 1];

                if (i >= x && dp[j - 1][i - x] != INF) {

                    long take = dp[j - 1][i - x]
                              + cost[i - x];

                    dp[j][i] = Math.min(dp[j][i], take);
                }
            }
        }

        return dp[k][n];
    }
}
