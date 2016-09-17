package LeetCode.round1.medium;

import java.util.Arrays;
import java.util.Random;

/**
 * 160917
Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();
 */
public class P384_ShuffleAnArray {

	private int[] origin;
	
	public P384_ShuffleAnArray(int[] nums){
		this.origin = nums;
	}
	
	 /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return origin;
    }
    
    /** Returns a random shuffling of the array. 
     *  AC: 226ms
     * */
    public int[] shuffle() {
    	Random ran = new Random();
    	int[] res = Arrays.copyOf(origin, origin.length);
    	for (int i = 0; i < res.length; i++) {
			//get a random index
    		int ranIdx = ran.nextInt(origin.length);
			
    		if(ranIdx != i){
    			//swap elemant @ random index and i
    			int tmp = res[i];
    			res[i] = res[ranIdx];
    			res[ranIdx] = tmp;
    		}
		}
        return res;
    }
	
	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3};
		P384_ShuffleAnArray p = new P384_ShuffleAnArray(nums);
		System.out.println(Arrays.toString(p.shuffle()));
		System.out.println(Arrays.toString(p.shuffle()));
		System.out.println(Arrays.toString(p.shuffle()));
		System.out.println(Arrays.toString(p.shuffle()));
		System.out.println(Arrays.toString(p.shuffle()));
		System.out.println(Arrays.toString(p.shuffle()));
		System.out.println(Arrays.toString(p.reset()));
	}

}
