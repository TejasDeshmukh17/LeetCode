class Solution {
    public int maxProduct(int[] nums) {
        int currentMax = nums[0];
        int currentMin = nums[0];
        int result = nums[0];

        for(int i = 1; i< nums.length ; i++)
        {
            int num = nums[i];

            int tempMax = Math.max(
                num,
                Math.max(
                    currentMax * num,

                    currentMin * num
                )
            );

            
            int tempMin = Math.min(
                num,
                Math.min(
                    currentMax * num,

                    currentMin * num
                )
            );

            currentMax = tempMax;
            currentMin = tempMin;

            result = Math.max(result, currentMax);
        }

        return result;
    }
}