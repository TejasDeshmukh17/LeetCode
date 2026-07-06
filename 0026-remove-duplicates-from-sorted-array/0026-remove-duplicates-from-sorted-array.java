class Solution {
    public int removeDuplicates(int[] nums) {
       int officer = 0;
       int n = nums.length;
       int res = 1;
       int cm =1;
       while (cm < n )
       {
        if(nums[cm] == nums[cm -1])
        {
            cm++;
            continue;
        }
        nums[officer+1]=nums[cm];
        officer++;
        res++;
        cm++;
       }
         return res;
    }
  
}
