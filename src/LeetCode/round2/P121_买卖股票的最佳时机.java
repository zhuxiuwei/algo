package LeetCode.round2;

import org.omg.CORBA.INTERNAL;

/**
 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 注意你不能在买入股票前卖出股票。

 示例 1:
 输入: [7,1,5,3,6,4]
 输出: 5
 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。

 示例 2:
 输入: [7,6,4,3,1]
 输出: 0
 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class P121_买卖股票的最佳时机 {

    /**
     * 双指针。感觉AC的有点无厘头。
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2)
            return 0;

        int left = 0, right = prices.length - 1, leftMin = Integer.MAX_VALUE, rightMax = Integer.MIN_VALUE, res = 0;
        while (left < right){
            if(prices[left] >= prices[right]){
                leftMin = prices[left] < leftMin ? prices[left]: leftMin;
                res = prices[left] - leftMin > res ? prices[left] - leftMin: res;   //如果不加上这行，{2, 4, 1}会错误返回0.
                left ++;
            }else {
                rightMax = prices[right] >= rightMax ? prices[right]: rightMax;
                right --;
            }
        }
        leftMin = prices[left] < leftMin ? prices[left]: leftMin;
        rightMax = prices[right] >= rightMax ? prices[right]: rightMax;
        return rightMax - leftMin > res ? rightMax - leftMin: res;
    }

    public static void main(String[] args) {
        P121_买卖股票的最佳时机 p = new P121_买卖股票的最佳时机();
        System.out.println(p.maxProfit(new int[]{1,2}));    //1
        System.out.println(p.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));   //5
        System.out.println(p.maxProfit(new int[]{5, 3, 2, 3, 2, 1, 4}));    //3
        System.out.println(p.maxProfit(new int[]{2, 4, 1}));    //2
    }
}
