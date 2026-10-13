
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] square = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            square[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(square);

        long ans = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i < n / 2) {
                ans -= square[i];
            } else {
                ans += square[i];
            }
        }

        return ans;
    }
}
