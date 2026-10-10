
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;
        int[] freq = new int[100001];

        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
        }

        for (int diff = 100000; diff > 0 && k > 0; diff--) {
            if (freq[diff] == 0) {
                continue;
            }

            long count = Math.min(k, (long) freq[diff]);
            freq[diff] -= count;
            freq[diff - 1] += count;
            k -= count;
        }

        long result = 0;

        for (int diff = 0; diff <= 100000; diff++) {
            result += (long) diff * diff * freq[diff];
        }

        return result;
    }
}
