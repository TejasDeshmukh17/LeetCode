
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;
        int[] freq = new int[100001];
        long sum = 0;

        for (int i = 0; i < nums1.length; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            freq[d]++;
            sum += d;
        }

        if (k >= sum) {
            return 0;
        }

        for (int d = 100000; d > 0 && k > 0; d--) {
            if (freq[d] == 0) {
                continue;
            }

            int next = d - 1;
            long count = Math.min(k, (long) freq[d]);

            freq[d] -= (int) count;
            freq[next] += (int) count;
            k -= count;
        }

        long result = 0;

        for (int d = 1; d <= 100000; d++) {
            result += (long) d * d * freq[d];
        }

        return result;
    }
}
