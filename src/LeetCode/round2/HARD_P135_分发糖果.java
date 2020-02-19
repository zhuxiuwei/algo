package LeetCode.round2;

/**
 200219 Hard
 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 每个孩子至少分配到 1 个糖果。
 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 那么这样下来，老师至少需要准备多少颗糖果呢？

 示例 1:
 输入: [1,0,2]
 输出: 5
 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。

 示例 2:
 输入: [1,2,2]
 输出: 4
 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 */
public class HARD_P135_分发糖果 {

    /**
     * 502 ms , 在所有 Java 提交中击败了 10.91% 的用户
     *
     * 总体不是很难。关键是思路。
     * 算法有点像贪心。在需要回头改的地方小复杂，！！！！ 有一个bug。
     */
    public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0)
            return 0;
        int[] assign = new int[ratings.length];
        assign[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if(ratings[i] == ratings[i - 1]) {
                assign[i] = 1;
            }else if(ratings[i] > ratings[i - 1]){
                assign[i] = assign[i - 1] + 1;
            }else {
                assign[i] = 1;
                if(assign[i - 1] == 1) {  //需要回头修改
                    int j = i;
                    while (true){
                        if(j > 0
                                && ratings[j - 1] > ratings[j]
                                && assign[j - 1] <= assign[j]){ //！！！！ bug： 少了这个条件的话，如{0,1,2,3,2,1}会错误返回12，因为3的糖果应该是4，但是被错误计算成3.
                            assign[j - 1] = assign[j] + 1;
                            j--;
                        }else
                            break;
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < assign.length; i++) {
            res += assign[i];
        }
        return res;
    }

    public static void main(String[] args) {
        HARD_P135_分发糖果 p = new HARD_P135_分发糖果();
        System.out.println(p.candy(new int[]{1,0,2}));  //5
        System.out.println(p.candy(new int[]{1,2,2}));  //4
        System.out.println(p.candy(new int[]{4,3,2,1}));  //10
        System.out.println(p.candy(new int[]{0,1,2,3,2,1}));  //13
    }
}
