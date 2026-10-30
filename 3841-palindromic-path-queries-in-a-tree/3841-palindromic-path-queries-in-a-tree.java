import java.util.*;

class Solution {

    int n;
    int LOG;

    int[] tin, tout, depth;
    int[][] up;

    List<Integer>[] adj;

    Fenwick bit;

    char[] chars;

    int timer;

    public List<Boolean> palindromePath(int n, int[][] edges,
                                        String s, String[] queries) {

        this.n = n;
        this.chars = s.toCharArray();

        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
            adj[v].add(u);
        }

        LOG = 1;

        while ((1 << LOG) <= n) {
            LOG++;
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];

        up = new int[LOG][n];

        timer = 0;

        buildEulerTour();

        bit = new Fenwick(2 * n);

        for (int i = 0; i < n; i++) {

            int mask = 1 << (chars[i] - 'a');

            bit.update(tin[i], mask);
            bit.update(tout[i], mask);
        }

        List<Boolean> result = new ArrayList<>();

        for (String q : queries) {

            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {

                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');

                int change = oldMask ^ newMask;

                bit.update(tin[u], change);
                bit.update(tout[u], change);

                chars[u] = c;

            } else {

                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLCA(u, v);

                int maskU = bit.query(tin[u] + 1);
                int maskV = bit.query(tin[v] + 1);

                // Correct path XOR formula
                int lcaChar = 1 << (chars[lca] - 'a');

                int pathMask = maskU ^ maskV ^ lcaChar;

                // At most one character can have odd frequency
                boolean possible =
                    (pathMask & (pathMask - 1)) == 0;

                result.add(possible);
            }
        }

        return result;
    }

    // Build Euler Tour and Binary Lifting table
    void buildEulerTour() {

        int[] parent = new int[n];

        Arrays.fill(parent, -1);

        int[] stack = new int[2 * n];
        int[] state = new int[n];

        int top = 0;

        stack[top++] = 0;

        while (top > 0) {

            int u = stack[top - 1];

            if (state[u] == 0) {

                state[u] = 1;

                tin[u] = timer++;

                up[0][u] =
                    (parent[u] == -1) ? u : parent[u];

                for (int i = 1; i < LOG; i++) {

                    up[i][u] =
                        up[i - 1][up[i - 1][u]];
                }

                for (int v : adj[u]) {

                    if (v == parent[u]) {
                        continue;
                    }

                    parent[v] = u;

                    depth[v] = depth[u] + 1;

                    stack[top++] = v;
                }

            } else {

                tout[u] = timer++;

                top--;
            }
        }
    }

    // Lowest Common Ancestor
    int getLCA(int u, int v) {

        if (depth[u] < depth[v]) {

            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int i = 0; i < LOG; i++) {

            if ((diff & (1 << i)) != 0) {

                u = up[i][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int i = LOG - 1; i >= 0; i--) {

            if (up[i][u] != up[i][v]) {

                u = up[i][u];
                v = up[i][v];
            }
        }

        return up[0][u];
    }

    // Fenwick Tree for XOR
    class Fenwick {

        int[] tree;
        int size;

        Fenwick(int size) {

            this.size = size;

            tree = new int[size + 1];
        }

        void update(int index, int value) {

            index++;

            while (index <= size) {

                tree[index] ^= value;

                index += index & -index;
            }
        }

        int query(int index) {

            int result = 0;

            while (index > 0) {

                result ^= tree[index];

                index -= index & -index;
            }

            return result;
        }
    }
}