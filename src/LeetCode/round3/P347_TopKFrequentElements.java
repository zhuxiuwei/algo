package LeetCode.round3;

import java.util.*;

/**
 * 240830 medium
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Constraints:
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class P347_TopKFrequentElements {

    /**
     * AC: Runtime 13ms Beats 73.06%, Memory 45.17 MB Beats 98.04%
     * 还行。
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> numToCountMap = new HashMap<>();  //k: num, v: 出现的次数
        for (int i = 0; i < nums.length; i++) {
            int count = numToCountMap.getOrDefault(nums[i], 0);
            numToCountMap.put(nums[i], ++ count);
        }

        //numToCountMap转成FreqCounter，并放到PriorityQueue。
        PriorityQueue<FreqCounter> queue = new PriorityQueue<>(Comparator.reverseOrder());  //！！！用优先级队列时，注意顺序！！
        numToCountMap.forEach((key, val) -> {
            FreqCounter counter = new FreqCounter(key, val);
            queue.offer(counter);
        });
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll().num;
        }
        return res;
    }

    static class FreqCounter implements Comparable<FreqCounter> {
        int num;
        int count;
        FreqCounter(int num, int count){
            this.num = num;
            this.count = count;
        }
        @Override
        public int compareTo(FreqCounter o) {
            if(this.count == o.count)
                return this.num - o.num;
            return this.count - o.count;
        }   //次数计数器
    }

    public static void main(String[] args) {
        P347_TopKFrequentElements p = new P347_TopKFrequentElements();
        System.out.println(Arrays.toString(p.topKFrequent(new int[]{1,1,1,2,2,3}, 2)));  // [1,2]
        System.out.println(Arrays.toString(p.topKFrequent(new int[]{1}, 1)));    //[1]
        System.out.println(Arrays.toString(p.topKFrequent(new int[]{2,2,1,3,4,5,3,2,2,2}, 1)));    //[2]
    }
}


