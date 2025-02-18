package LeetCode.round3;

/**
 * 250217 medium
 * https://leetcode.com/problems/gas-station/description/?envType=study-plan-v2&envId=top-interview-150
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station.
 * You begin the journey with an empty tank at one of the gas stations.
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction,
 * otherwise return -1. If there exists a solution, it is guaranteed to be unique.
 *
 * Example 1:
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * Output: 3
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 *
 * Example 2:
 * Input: gas = [2,3,4], cost = [3,4,3]
 * Output: -1
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 *
 * Constraints:
 * n == gas.length == cost.length
 * 1 <= n <= 10^5
 * 0 <= gas[i], cost[i] <= 10^4
 */
public class P134_GasStation {

    /**
     * AC： Runtime 3 ms Beats 38.26%。 Memory 59.01 MB Beats 6.02%
     * 思路：
     * 取temp[k] = gas[k] - cost[k]。temp[k]相当于从k开始，跑到下一站后，车辆剩余的gas。
     * 问题就变成了：找到idx k，在k到k期后所有元素sum过程中，sum的和一直>=0。
     *
     * O(n)解法：【思路和下面暴力法的一样，但是解法变成了O(n)】
     * i: from 0 to len(gas) - 1:
     *      tmp += temp[j]
     *      如果中途tmp<0了，则这个i不符合条件，i取下一个重新开始轮回。
     *  如果tmp>=0，则返回i 即可。
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //运行注释所说的思路。时间复杂度O(n^2)
        int len = gas.length;
        int temp[] = new int[len * 2];   //长度设置成2倍，是为了避免处理循环数组的复杂。空间换复杂度。【！！！！！！最开始没这么做，各种错！！！！！】
        for (int i = 0; i < len; i++) {
            temp[i] = gas[i] - cost[i];
            temp[i + len] = gas[i] - cost[i];
        }
        for (int i = 0; i < len; i++) {
            int tempSum = 0;
            boolean ok = true;
            for (int j = i; j < i + len; j++) {
                tempSum += temp[j];
                if(tempSum < 0) {
                    i = j;      //上面虽然是两重循环，但是第二重循环如果提前结束，第一重循环会跳过二重循环中已经遍历过的元素，所以最后是一重循环。
                    ok = false;
                    break;
                }
            }
            if(ok)
                return i;
        }
        return  -1;
    }
    /**
     * 暴力法。在超长的case里会超时。
     * ！！！虽然思路比较平铺直叙，但是过程还是各种出错。
     * 思路：
     * 取temp[k] = gas[k] - cost[k]。temp[k]相当于从k开始，跑到下一站后，车辆剩余的gas。
     * 问题就变成了：找到idx k，在k到k期后所有元素su m过程中，sum的和一直>=0。
     *
     * O(n^2)解法：
     * i: from 0 to len(gas) - 1:
     *  j: from i to i-1; （遇到数组尾巴则从头循环遍历）
     *      tmp += temp[j]
     *      如果中途tmp<0了，则这个i不符合条件，需要跳出j的循环，判断下一个i。
     *  如果tmp>=0，则返回i 即可。
     */
    public int canCompleteCircuit_baoli_timeout(int[] gas, int[] cost) {

        //运行注释所说的思路。时间复杂度O(n^2)
        int len = gas.length;
        int temp[] = new int[len * 2];   //长度设置成2倍，是为了避免处理循环数组的复杂。空间换复杂度。【！！！！！！最开始没这么做，各种错！！！！！】
        for (int i = 0; i < len; i++) {
            temp[i] = gas[i] - cost[i];
            temp[i + len] = gas[i] - cost[i];
        }
        for (int i = 0; i < len; i++) {
            int tempSum = 0;
            boolean ok = true;
            for (int j = i; j < i + len; j++) { //！！！ 这里的条件各种写错好几次。！！！！！
                tempSum += temp[j];
                if(tempSum < 0) {
                    ok = false;     //！！！！不能省略这个判断
                    break;
                }
            }
            if(ok)  //！！！！不能省略这个判断
                return i;
        }
        return -1;
    }


    public static void main(String[] args) {
        P134_GasStation p = new P134_GasStation();
        int[] gas = new int[]{2,3,4};
        int[] cost = new int[]{3,4,3};
        System.out.println(p.canCompleteCircuit(gas, cost));    //-1
        gas = new int[]{1,2,3,4,5};
        cost = new int[]{3,4,5,1,2};
        System.out.println(p.canCompleteCircuit(gas, cost));    //3
        gas = new int[]{3,1,1};
        cost = new int[]{1,2,2};
        System.out.println(p.canCompleteCircuit(gas, cost));    //0     ！！！这是一个容易出错的case

        //自创的一个例子
        gas = new int[]{1,8,1,4,1,6,2};
        cost = new int[]{4,2,9,2,2,3,1};
        System.out.println(p.canCompleteCircuit(gas, cost));    //3
    }
}
