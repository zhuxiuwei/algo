package LeetCode.round1.medium;
/**
 * 160930
There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. 
On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). 
For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.

Example:
Given n = 3. 
At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off]. 
So you should return 1, because there is only one bulb is on.
 */
public class P319_BulbSwitcher {

	/**
	 * AC: 0ms, 25.6%
	 * 就是找的规律，不知道具体啥原理~~~~
	 */
	public int bulbSwitch(int n) {
		return (int)Math.pow(n, 0.5);
    }
	
	public static void main(String[] args) {
		P319_BulbSwitcher p = new P319_BulbSwitcher();
		System.out.println(p.bulbSwitch(9));
	}

}
