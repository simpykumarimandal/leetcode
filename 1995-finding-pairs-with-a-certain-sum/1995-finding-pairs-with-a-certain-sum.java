import java.util.HashMap;
import java.util.Map;


class FindSumPairs {

    private int[] nums1; 
    private int[] nums2; 
    private Map<Integer, Integer> nums2Freq; 

   
    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.nums2Freq = new HashMap<>();

       
        for (int num : nums2) {
            nums2Freq.put(num, nums2Freq.getOrDefault(num, 0) + 1);
        }
    }

    
    public void add(int index, int val) {
       
        int oldValue = nums2[index];
        
        
        nums2Freq.put(oldValue, nums2Freq.get(oldValue) - 1);

        nums2[index] += val;
        
      
        int newValue = nums2[index];
        
    
        nums2Freq.put(newValue, nums2Freq.getOrDefault(newValue, 0) + 1);
    }

    public int count(int tot) {
        int pairCount = 0;
        
       
        for (int num1 : nums1) {
           
            int targetNum2 = tot - num1;
            
            pairCount += nums2Freq.getOrDefault(targetNum2, 0);
        }
        
        return pairCount;
    }
}
