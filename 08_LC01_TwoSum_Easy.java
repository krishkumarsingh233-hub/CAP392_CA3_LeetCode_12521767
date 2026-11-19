class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> resMap = new HashMap<>();

        for(int i=0; i< nums.length; i++){
            int key = target-nums[i]; // 2
            if(resMap.containsKey(key)){
                return new int[]{resMap.get(key), i};
            }
                resMap.put(nums[i], i);
            
        }

        
        return new int[]{};
        
    }
}
