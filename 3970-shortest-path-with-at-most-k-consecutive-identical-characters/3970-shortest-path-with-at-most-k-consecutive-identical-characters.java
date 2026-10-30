import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {

        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        // dist[node][count] = minimum distance
        // count = consecutive occurrences of current label
        int[][] dist = new int[n][k + 1];

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        );

        // State: {node, consecutiveCount, distance}
        dist[0][1] = 0;
        pq.offer(new int[]{0, 1, 0});

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();

            int node = curr[0];
            int count = curr[1];
            int cost = curr[2];

            if (cost != dist[node][count]) {
                continue;
            }

            // Destination reached
            if (node == n - 1) {
                return cost;
            }

            for (int[] edge : graph[node]) {

                int next = edge[0];
                int weight = edge[1];

                int nextCount;

                if (labels.charAt(node) == labels.charAt(next)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                // More than k consecutive identical characters
                if (nextCount > k) {
                    continue;
                }

                int newCost = cost + weight;

                if (newCost < dist[next][nextCount]) {

                    dist[next][nextCount] = newCost;

                    pq.offer(new int[]{
                        next,
                        nextCount,
                        newCost
                    });
                }
            }
        }

        return -1;
    }
}
