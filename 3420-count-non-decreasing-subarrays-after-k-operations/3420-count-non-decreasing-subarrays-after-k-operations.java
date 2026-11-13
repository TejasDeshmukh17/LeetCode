class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int log = 1;

        while ((1 << log) <= n) {
            log++;
        }

        int[] next = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }

            next[i] = top == -1 ? n : stack[top];
            stack[++top] = i;
        }

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int[][] up = new int[log][n];
        long[][] sum = new long[log][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = next[i];
            sum[0][i] = (long) nums[i] * (next[i] - i)
                    - (prefix[next[i]] - prefix[i]);
        }

        for (int j = 1; j < log; j++) {
            for (int i = 0; i < n; i++) {
                int mid = up[j - 1][i];

                if (mid < n) {
                    up[j][i] = up[j - 1][mid];
                    sum[j][i] = sum[j - 1][i] + sum[j - 1][mid];
                } else {
                    up[j][i] = n;
                    sum[j][i] = sum[j - 1][i];
                }
            }
        }

        long ans = 0;

        for (int r = 0; r < n; r++) {
            int low = 0;
            int high = r;

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (getCost(mid, r, nums, prefix, up, sum, log) <= k) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            ans += r - low + 1;
        }

        return ans;
    }

    private long getCost(int l, int r, int[] nums,
                         long[] prefix, int[][] up,
                         long[][] sum, int log) {

        int cur = l;
        long cost = 0;

        for (int j = log - 1; j >= 0; j--) {
            if (up[j][cur] <= r) {
                cost += sum[j][cur];
                cur = up[j][cur];
            }
        }

        cost += (long) nums[cur] * (r - cur + 1)
                - (prefix[r + 1] - prefix[cur]);

        return cost;
    }
}