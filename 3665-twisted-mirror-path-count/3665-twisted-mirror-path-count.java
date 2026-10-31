class Solution {
    static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long[][] dp = new long[m][n];

        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (dp[i][j] == 0)
                    continue;

                // Move RIGHT
                if (j + 1 < n) {
                    int r = i;
                    int c = j + 1;
                    int dir = 0; // 0 = right, 1 = down

                    while (true) {
                        if (r >= m || c >= n)
                            break;

                        if (grid[r][c] == 0) {
                            dp[r][c] = (dp[r][c] + dp[i][j]) % MOD;
                            break;
                        }

                        // Mirror
                        if (dir == 0) {
                            // Right -> Down
                            dir = 1;
                            r++;
                        } else {
                            // Down -> Right
                            dir = 0;
                            c++;
                        }
                    }
                }

                // Move DOWN
                if (i + 1 < m) {
                    int r = i + 1;
                    int c = j;
                    int dir = 1; // 0 = right, 1 = down

                    while (true) {
                        if (r >= m || c >= n)
                            break;

                        if (grid[r][c] == 0) {
                            dp[r][c] = (dp[r][c] + dp[i][j]) % MOD;
                            break;
                        }

                        // Mirror
                        if (dir == 0) {
                            // Right -> Down
                            dir = 1;
                            r++;
                        } else {
                            // Down -> Right
                            dir = 0;
                            c++;
                        }
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}