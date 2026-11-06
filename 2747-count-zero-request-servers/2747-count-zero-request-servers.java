import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        
        // Sort logs by time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // Store query with its original index
        int[][] q = new int[queries.length][2];

        for (int i = 0; i < queries.length; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }

        // Sort queries by time
        Arrays.sort(q, (a, b) -> Integer.compare(a[0], b[0]));

        int[] freq = new int[n + 1];
        int[] ans = new int[queries.length];

        int left = 0;
        int right = 0;

        // Number of servers having at least one request
        int active = 0;

        for (int[] query : q) {

            int time = query[0];

            // Add logs with time <= query time
            while (right < logs.length && logs[right][1] <= time) {
                int server = logs[right][0];

                if (freq[server] == 0) {
                    active++;
                }

                freq[server]++;
                right++;
            }

            // Remove logs with time < query - x
            int start = time - x;

            while (left < right && logs[left][1] < start) {
                int server = logs[left][0];

                freq[server]--;

                if (freq[server] == 0) {
                    active--;
                }

                left++;
            }

            // Servers with zero requests
            ans[query[1]] = n - active;
        }

        return ans;
    }
}