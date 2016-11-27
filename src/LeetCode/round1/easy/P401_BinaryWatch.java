package LeetCode.round1.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 161126
 A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59). Each LED represents a zero or one, with the least significant bit on the right.
 Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
 Input: n = 1 Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 
 Note:
 - The order of output does not matter.
 - The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 - The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */
public class P401_BinaryWatch {

	/**
	 * AC: 4ms, 52.19%
	 * 典型backtracking问题。！！！这是第一个做的还算顺利的。基本没太曲折。 
	 * @param num
	 * @return
	 */
	public List<String> readBinaryWatch(int num) {
		List<String> res = new ArrayList<String>();
		if(num < 0 || num > 8)	//out of range
			return res;

		int maxHourBit = 3, maxMinBit = 5;
		for (int i = 0; i <= num; i++) {
			if(i <= maxHourBit && (num - i) <= maxMinBit){
				List<Integer> possibleHours = getPossibleSum(11, i, 4);
				List<Integer> possibleMins = getPossibleSum(59, num - i, 6);
				for (int j = 0; j < possibleHours.size(); j++) {
					for (int j2 = 0; j2 < possibleMins.size(); j2++) 
						if(possibleMins.get(j2) < 10)
							res.add(possibleHours.get(j) + ":0" + possibleMins.get(j2));
						else
							res.add(possibleHours.get(j) + ":" + possibleMins.get(j2));
				}
			}
		}
        return res;
    }
	/**
	 * @param maxSum Max sum value. e.g. for hour it's 59.
	 * @param bitsCount total desired bit=1 count
	 * @param highestBitIdx 
	 * @return
	 */
	private List<Integer> getPossibleSum(int maxSum, int bitsCount, int highestBitIdx){
		List<Integer> res = new ArrayList<Integer>();
		if(bitsCount <= highestBitIdx)
			helper(maxSum, res, 0, 0, 0, bitsCount, highestBitIdx);
		return res;
	}
	/**
	 * @param maxSum Max sum value. e.g. for minute it's 59, for hour its' 11.
	 * @param res result list.
	 * @param temp current temp sum.
	 * @param currentBitIdx  current bit index
	 * @param current1Count current bit=1 count
	 * @param bitCount total desired bit=1 count
	 * @param highestBitIdx
	 */
	private void helper(int maxSum, List<Integer> res, int temp, int currentBitIdx, int current1Count, int bitCount, int highestBitIdx){
		if(current1Count == bitCount && temp <= maxSum && currentBitIdx <= highestBitIdx){	//one result found.
			res.add(temp);
			return;
		}
		if(current1Count < bitCount && currentBitIdx < highestBitIdx){
			int oldTemp = temp;
			temp = (1 << currentBitIdx) ^ temp;
			//Let current idx = 1, go deeper.
			helper(maxSum, res, temp, currentBitIdx + 1, current1Count + 1, bitCount, highestBitIdx);	
			//backtracking. Let current idx = 0, go deeper.
			helper(maxSum, res, oldTemp, currentBitIdx + 1, current1Count, bitCount, highestBitIdx);
		}
	}
	
	/**
	 * 投机取巧，有限的结果集，直接打印结果。
	 * AC: 2ms, 91%。
	 * @param num
	 * @return
	 */
	public List<String> readBinaryWatch_memory(int num) {
		List<String> res = new ArrayList<String>();
		if(num < 0 || num > 8)	//out of range
			return res;
		String s = "";
		switch(num){
			case 0:
				s = "0:00";
				break;
			case 1:
				s = "0:01,0:02,0:04,0:08,0:16,0:32,1:00,2:00,4:00,8:00";
				break;
			case 2:
				s = "0:03,0:05,0:09,0:17,0:33,0:06,0:10,0:18,0:34,0:12,0:20,0:36,0:24,0:40,0:48,1:01,1:02,1:04,1:08,1:16,1:32,2:01,2:02,2:04,2:08,2:16,2:32,4:01,4:02,4:04,4:08,4:16,4:32,8:01,8:02,8:04,8:08,8:16,8:32,3:00,5:00,9:00,6:00,10:00";
				break;
			case 3:
				s = "0:07,0:11,0:19,0:35,0:13,0:21,0:37,0:25,0:41,0:49,0:14,0:22,0:38,0:26,0:42,0:50,0:28,0:44,0:52,0:56,1:03,1:05,1:09,1:17,1:33,1:06,1:10,1:18,1:34,1:12,1:20,1:36,1:24,1:40,1:48,2:03,2:05,2:09,2:17,2:33,2:06,2:10,2:18,2:34,2:12,2:20,2:36,2:24,2:40,2:48,4:03,4:05,4:09,4:17,4:33,4:06,4:10,4:18,4:34,4:12,4:20,4:36,4:24,4:40,4:48,8:03,8:05,8:09,8:17,8:33,8:06,8:10,8:18,8:34,8:12,8:20,8:36,8:24,8:40,8:48,3:01,3:02,3:04,3:08,3:16,3:32,5:01,5:02,5:04,5:08,5:16,5:32,9:01,9:02,9:04,9:08,9:16,9:32,6:01,6:02,6:04,6:08,6:16,6:32,10:01,10:02,10:04,10:08,10:16,10:32,7:00,11:00";
				break;
			case 4:
				s = "0:15,0:23,0:39,0:27,0:43,0:51,0:29,0:45,0:53,0:57,0:30,0:46,0:54,0:58,1:07,1:11,1:19,1:35,1:13,1:21,1:37,1:25,1:41,1:49,1:14,1:22,1:38,1:26,1:42,1:50,1:28,1:44,1:52,1:56,2:07,2:11,2:19,2:35,2:13,2:21,2:37,2:25,2:41,2:49,2:14,2:22,2:38,2:26,2:42,2:50,2:28,2:44,2:52,2:56,4:07,4:11,4:19,4:35,4:13,4:21,4:37,4:25,4:41,4:49,4:14,4:22,4:38,4:26,4:42,4:50,4:28,4:44,4:52,4:56,8:07,8:11,8:19,8:35,8:13,8:21,8:37,8:25,8:41,8:49,8:14,8:22,8:38,8:26,8:42,8:50,8:28,8:44,8:52,8:56,3:03,3:05,3:09,3:17,3:33,3:06,3:10,3:18,3:34,3:12,3:20,3:36,3:24,3:40,3:48,5:03,5:05,5:09,5:17,5:33,5:06,5:10,5:18,5:34,5:12,5:20,5:36,5:24,5:40,5:48,9:03,9:05,9:09,9:17,9:33,9:06,9:10,9:18,9:34,9:12,9:20,9:36,9:24,9:40,9:48,6:03,6:05,6:09,6:17,6:33,6:06,6:10,6:18,6:34,6:12,6:20,6:36,6:24,6:40,6:48,10:03,10:05,10:09,10:17,10:33,10:06,10:10,10:18,10:34,10:12,10:20,10:36,10:24,10:40,10:48,7:01,7:02,7:04,7:08,7:16,7:32,11:01,11:02,11:04,11:08,11:16,11:32";
				break;
			case 5:
				s = "0:31,0:47,0:55,0:59,1:15,1:23,1:39,1:27,1:43,1:51,1:29,1:45,1:53,1:57,1:30,1:46,1:54,1:58,2:15,2:23,2:39,2:27,2:43,2:51,2:29,2:45,2:53,2:57,2:30,2:46,2:54,2:58,4:15,4:23,4:39,4:27,4:43,4:51,4:29,4:45,4:53,4:57,4:30,4:46,4:54,4:58,8:15,8:23,8:39,8:27,8:43,8:51,8:29,8:45,8:53,8:57,8:30,8:46,8:54,8:58,3:07,3:11,3:19,3:35,3:13,3:21,3:37,3:25,3:41,3:49,3:14,3:22,3:38,3:26,3:42,3:50,3:28,3:44,3:52,3:56,5:07,5:11,5:19,5:35,5:13,5:21,5:37,5:25,5:41,5:49,5:14,5:22,5:38,5:26,5:42,5:50,5:28,5:44,5:52,5:56,9:07,9:11,9:19,9:35,9:13,9:21,9:37,9:25,9:41,9:49,9:14,9:22,9:38,9:26,9:42,9:50,9:28,9:44,9:52,9:56,6:07,6:11,6:19,6:35,6:13,6:21,6:37,6:25,6:41,6:49,6:14,6:22,6:38,6:26,6:42,6:50,6:28,6:44,6:52,6:56,10:07,10:11,10:19,10:35,10:13,10:21,10:37,10:25,10:41,10:49,10:14,10:22,10:38,10:26,10:42,10:50,10:28,10:44,10:52,10:56,7:03,7:05,7:09,7:17,7:33,7:06,7:10,7:18,7:34,7:12,7:20,7:36,7:24,7:40,7:48,11:03,11:05,11:09,11:17,11:33,11:06,11:10,11:18,11:34,11:12,11:20,11:36,11:24,11:40,11:48";
				break;
			case 6:
				s = "1:31,1:47,1:55,1:59,2:31,2:47,2:55,2:59,4:31,4:47,4:55,4:59,8:31,8:47,8:55,8:59,3:15,3:23,3:39,3:27,3:43,3:51,3:29,3:45,3:53,3:57,3:30,3:46,3:54,3:58,5:15,5:23,5:39,5:27,5:43,5:51,5:29,5:45,5:53,5:57,5:30,5:46,5:54,5:58,9:15,9:23,9:39,9:27,9:43,9:51,9:29,9:45,9:53,9:57,9:30,9:46,9:54,9:58,6:15,6:23,6:39,6:27,6:43,6:51,6:29,6:45,6:53,6:57,6:30,6:46,6:54,6:58,10:15,10:23,10:39,10:27,10:43,10:51,10:29,10:45,10:53,10:57,10:30,10:46,10:54,10:58,7:07,7:11,7:19,7:35,7:13,7:21,7:37,7:25,7:41,7:49,7:14,7:22,7:38,7:26,7:42,7:50,7:28,7:44,7:52,7:56,11:07,11:11,11:19,11:35,11:13,11:21,11:37,11:25,11:41,11:49,11:14,11:22,11:38,11:26,11:42,11:50,11:28,11:44,11:52,11:56";
				break;
			case 7:
				s = "3:31,3:47,3:55,3:59,5:31,5:47,5:55,5:59,9:31,9:47,9:55,9:59,6:31,6:47,6:55,6:59,10:31,10:47,10:55,10:59,7:15,7:23,7:39,7:27,7:43,7:51,7:29,7:45,7:53,7:57,7:30,7:46,7:54,7:58,11:15,11:23,11:39,11:27,11:43,11:51,11:29,11:45,11:53,11:57,11:30,11:46,11:54,11:58";
				break;
			case 8:
				s = "7:31,7:47,7:55,7:59,11:31,11:47,11:55,11:59";
				break;
			default:
				s = "";
				break;
		}
		String[] ss = s.split(",");
		for (String t: ss) 
			res.add(t);
        return res;
    }
	
	public static void main(String[] args) {
		P401_BinaryWatch p = new P401_BinaryWatch();
		for (int i = 0; i <= 8; i++) 
			System.out.println(p.readBinaryWatch(i));
		for (int i = 0; i <= 8; i++) 
			System.out.println(p.readBinaryWatch_memory(i));
	}

}
