class Solution {
    static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;

        long[] sum = new long[4 * n];
        long[] square = new long[4 * n];
        long[] lazy = new long[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int left = last[x];
            last[x] = i + 1;

            update(1, 0, n - 1, left, i, sum, square, lazy);

            ans = (ans + square[1]) % MOD;
        }

        return (int) ans;
    }

    private void update(int node, int start, int end,
                        int l, int r, long[] sum,
                        long[] square, long[] lazy) {
        if (r < start || end < l) return;

        if (l <= start && end <= r) {
            long len = end - start + 1L;
            square[node] = (square[node] + 2L * sum[node] + len) % MOD;
            sum[node] = (sum[node] + len) % MOD;
            lazy[node] = (lazy[node] + 1) % MOD;
            return;
        }

        push(node, start, end, sum, square, lazy);

        int mid = (start + end) / 2;

        update(node * 2, start, mid, l, r, sum, square, lazy);
        update(node * 2 + 1, mid + 1, end, l, r, sum, square, lazy);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        square[node] = (square[node * 2] + square[node * 2 + 1]) % MOD;
    }

    private void push(int node, int start, int end,
                      long[] sum, long[] square, long[] lazy) {
        if (lazy[node] == 0 || start == end) return;

        int mid = (start + end) / 2;
        long add = lazy[node];

        apply(node * 2, start, mid, add, sum, square, lazy);
        apply(node * 2 + 1, mid + 1, end, add, sum, square, lazy);

        lazy[node] = 0;
    }

    private void apply(int node, int start, int end, long add,
                       long[] sum, long[] square, long[] lazy) {
        long len = end - start + 1L;

        square[node] = (square[node]
                + 2L * add % MOD * sum[node] % MOD
                + add * add % MOD * len % MOD) % MOD;

        sum[node] = (sum[node] + add * len) % MOD;
        lazy[node] = (lazy[node] + add) % MOD;
    }
}