class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        long ans = 0;

        // Count circular descents in the entire array.
        int[] desc = new int[n + 1];

        for (int i = 0; i < n; i++) {
            desc[i + 1] = desc[i];
            if (nums[i] > nums[(i + 1) % n]) {
                desc[i + 1]++;
            }
        }

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean valid = true;
            int prevMax = Integer.MIN_VALUE;

            for (int start = 0; start < n; start += k) {
                int end = start + k;

                int drops = 0;
                for (int j = start; j < end - 1; j++) {
                    if (nums[j] > nums[j + 1]) drops++;
                }

                if (nums[end - 1] > nums[start]) drops++;

                if (drops > 1) {
                    valid = false;
                    break;
                }

                // Find the minimum and maximum in this block.
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;

                for (int j = start; j < end; j++) {
                    min = Math.min(min, nums[j]);
                    max = Math.max(max, nums[j]);
                }

                if (min < prevMax) {
                    valid = false;
                    break;
                }

                prevMax = max;
            }

            if (valid) ans += k;
        }

        return (int) ans;
    }
}