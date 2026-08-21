import java.util.*;

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        // List to store all unique triplets
        List<List<Integer>> result = new ArrayList<>();

        // Step 1: Sort the array
        Arrays.sort(nums);

        // Step 2: Fix one element
        for (int i = 0; i < nums.length - 2; i++) {

            // Skip duplicate values
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // If current number is positive,
            // remaining numbers will also be positive
            if (nums[i] > 0) {
                break;
            }

            // Step 3: Initialize two pointers
            int left = i + 1;
            int right = nums.length - 1;

            // Step 4: Find pairs using two pointers
            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                // If sum is less than 0, increase left
                if (sum < 0) {
                    left++;
                }

                // If sum is greater than 0, decrease right
                else if (sum > 0) {
                    right--;
                }

                // If sum is equal to 0
                else {

                    // Add the triplet
                    result.add(Arrays.asList(
                        nums[i],
                        nums[left],
                        nums[right]
                    ));

                    // Move both pointers
                    left++;
                    right--;

                    // Skip duplicate values from left
                    while (left < right &&
                           nums[left] == nums[left - 1]) {
                        left++;
                    }

                    // Skip duplicate values from right
                    while (left < right &&
                           nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }

        // Return all unique triplets
        return result;
    }
}