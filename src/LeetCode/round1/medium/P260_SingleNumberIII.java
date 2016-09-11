package LeetCode.round1.medium;

import java.util.Arrays;

/**
 * 160911
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
For example:
Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity? 
 */
public class P260_SingleNumberIII {

	public int[] singleNumber(int[] nums) {
		
		//step1: 异或所有数字，得到两个唯一出现一次的数字的diff
		int diff = 0;
		for (int i = 0; i < nums.length; i++) 
			diff = diff ^ nums[i];
		
		
		//step2: 取出diff的1个为1的bit，说明两个唯一出现一次的数字在这个bit上是不一样的。 （注意，这里用到了异或的"diff"性质 -- 两个bit异或为1，说明两个bit不相等。我们利用这个性质，给原数组分组。）
		int chosenBit = 1;
		while(true)
			if((diff & chosenBit) > 0) 
				break;
			else
				chosenBit *= 2;
		
		/*step3. 然后把数组按照这个chosen bit分成两组 -- chosenBit所在的bit为1的一组，为0的是另一组。这这两组一定符合以下规律：
			1. 两个只出现一次的数字，被分别分到了不同的组；
			2. 所有出现2次的数字，其两个数字一定被分到了同一组中。
			
			然后对两组分别异或，就是最终结果。
		*/
		int diff1 = 0, diff2 = 0;
		for (int i = 0; i < nums.length; i++) {
			if((nums[i] & chosenBit) > 0) //group 1
				diff1 = diff1 ^ nums[i];
			else
				diff2 = diff2 ^ nums[i];
		}
		
        return new int[]{diff1, diff2};
    }
	
	public static void main(String[] args) {
		P260_SingleNumberIII p = new P260_SingleNumberIII();
		System.out.println(Arrays.toString(p.singleNumber(new int[]{4,10,2,3,3,2,1,4})));
	}

}

/**
  还是异或的bit操作，对于一次全异或后，如何区分两个唯一出现一次数字没有想出好的思路。看的答案 http://www.cnblogs.com/Anthony-Wang/p/5048762.html。Bit操作的算法还是不熟练。  
  有思路后，一次AC。
 */
