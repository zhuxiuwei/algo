top100 题目总结 2020.2
=
[1. Two Sum - easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P001_TwoSum.java) ★★
* Easy的居然还是错了一次。和第一轮错误一样。

[445. Add Two Numbers II - Medium (非top100)](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P445_AddTwoNumbersII.java) ★★★
* 总体还算顺利，循环里的进位问题处理，还是有点乱。 debug了一下。

[2. Add Two Numbers - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P002_AddTwoNumbers.java) ★★
*  总体还算顺利，在ListNode shaobing、ListNode res的关系(29-30行)、保证返回第一个节点这儿花了点时间。

[3. Longest Substring Without Repeating Characters - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P003_LongestSubstringWithoutRepeatingCharacters.java) ★★
*  总体还算顺利，但是写的不快，有俩边界条件bug。

[4. 寻找两个有序数组的中位数 - Hard](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/HARD_P004_寻找两个有序数组的中位数_我.java) ★★★★★
* 面Contiva时遇到这个题的变种。Contiva是要找到两个sorted array最中间的number。
* 最后抄的标准答案。自己实现了一遍。思路和我的差的不太大，都是二分变体，不断收敛两个指针。但是写起来更有技巧。

[5. Longest Palindromic Substring - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P005_LongestPalindromicSubstring.java) ★★★★★
* solution： https://leetcode.com/articles/longest-palindromic-substring/
* 花了大概20分钟，思路：查找翻转s得到s'，然后找s和s'的最大公共字符串。 第一次还写错了（见longestPalindrome_Wrong），上面solution里说这是个常见错误。
* solution提示可以通过index解决上述错误，改了，比较ugly(用了java原生的indexOf)，通过了，但是还是没AC，因为超时了。
* TODO: 有时间看看solution里的动态规划解法。
* 和之前九章算法、leetcode时刷过的[P409_LongestPalindrome](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P409_LongestPalindrome.java)的统计回文个数的题目不一样，那个简单很多。

[11. Container With Most Water - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P011_ContainerWithMostWater.java) ★★★★
* 和另一个题目 水池蓄水 https://leetcode.com/problems/trapping-rain-water/ 不完全一样。
* 双指针问题，自己写了两个错误方案，一是指针碰撞方案，二是双指针两轮遍历 方案，都不对；
* 最后看了[solution](https://leetcode.com/problems/container-with-most-water/solution/)，还是双指针碰撞，O(n)

[15. 3Sum - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P015_3Sum.java)
* 自己的方法(threeSum_overtime)比较复杂，主要负载在要自定义一个数据结构 + hash做去重，导致算法在超大数据超时。 写了快一个小时，不是特别顺利
* 参考了其他人的思路，双指针碰撞，比较优雅 ，虽然和我算法时间复杂度都是O(n^2)但是可能因为不需要hash去重，快很多。 实现的时候问题不太大。

[20. Valid Parentheses - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P020_ValidParentheses.java) ★
* 简单，但还是有一个低级错误。不是一次bug free。

[7. 整数翻转 - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P007_整数翻转.java) ★★
* 非100题，为阿里题库。
* 不考虑溢出的话比较简单。 我用了投机取巧的办法。 把返回结果先变成long，在和Interger的上限比较。
* 此外需要注意负数情况。
* 网上看到不少优雅的解法，但是不想花时间理解了。

[9. Palindrome Number - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P009_PalindromeNumber.java) ★★
* 非100题，为阿里题库。
* 和第七题[7. Reverse Integer - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P007_Reverse_Integer.java)很像。
* 不考虑溢出的话比较简单。 我用了投机取巧的办法。 把返回结果先变成long，在和Interger的上限比较。

[8. 字符串转换整数 (atoi) - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P008_字符串转换整数atoi.java) ★★★
* 非100题，为阿里题库。
* 不是很难，但是很容易出错。
* 共产生三个的bug。
* 和7,9类似，还是被整数上下界溢出折腾够呛。

[141. 环形链表 - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P008_字符串转换整数atoi.java) ★
* 非100题，为阿里题库。
* 不算难。 需要细心点。

[19. 删除链表的倒数第N个节点 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P008_字符串转换整数atoi.java) ★
* 非100题，为阿里题库。
* 一次通过

[33. 搜索旋转排序数组 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P033_搜索旋转排序数组.java) ★★
* 非100题，为阿里题库。
* 二分查找的变形。 问题不是太大，有一个bug。

[206. 反转链表 - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P206_反转链表.java) ★
* 字节跳动题库。
* 指针操作算法，一次AC。
* 问题： 递归算法，存在一个bug。和java reference在递归最底层返回的值，在上层递归变null了有关。

[21. 合并两个有序链表 - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P021_合并两个有序链表.java) ★★
* 字节跳动题库。
* 不是特别顺利。虽然easy，还是先写错了一版。后又实现了一版，还是存在一个易犯错的地方。

[42. 接雨水 - Hard](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/HARD_P042_接雨水.java) ★★★
* 字节跳动题库。
* 直接看的双指针的解释。自己想不容易。https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode/

[146. LRU缓存机制 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/lintcode/round1/P134_LRUCache.java) ★★
* 字节跳动题库。
* 没做，只看了之前的代码。总体是新定义了LRUCacheNode类，扩展了链表Node，每个node有prev和next两个指针。用一个HashMap<Integer, LRUCacheNode>做LRU的缓存。并且整个类维护tail(最近使用)和head(最久没使用)两个指针。
* get操作若找到需要更新tail；set操作需要更新tail(分key是否已存在两种情况)，同时注意是不是超过了LRU的capacity，超过了还要删除head。
* 不是很难，条件多了点需要注意到。

[121. 买卖股票的最佳时机 - EASY](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P121_买卖股票的最佳时机.java) ★★★
* 字节跳动题库。
* 我本轮采用了双指针的解法，感觉最后虽然AC了，但是还是不能解释为啥。。。
* 还是[第一轮](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P121_BestTimeToBuyAndSellStock.java)的方案：做的先取每天的delta，然后找出连续 max >0 的delta思路比较好解释。

[160.相交链表 - EASY](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P160_相交链表.java) ★
* 字节跳动题库。
* 不难，但还是搞出一个bug。

[56.合并区间 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P160_相交链表.java) ★★
* 字节跳动题库。
* 不难，主要是需要手写快排。快排部分看了以前的代码。

[23. 合并K个排序链表 - Hard](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/HARD_P023_合并K个排序链表.java) ★★
* 字节跳动题库。
* 用了PriorityQueue，不难。

[135. 分发糖果 - Hard](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/HARD_P135_分发糖果.java) ★★
* 字节跳动题库。
* 总体不是很难。关键是思路。算法有点像贪心。在需要回头改的地方小复杂，！！！！ 有一个bug。

[46. 全排列 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P046_Permutations.java)★★★
* 字节跳动题库。
* 典型回溯问题。 没做。TODO: 看一下之前实现的代码。

[215. 数组中的第K个最大元素 - Medium]](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P215_KthLargestElementInAnArray.java)★★
* 字节跳动题库。
* 没做，之前做过。用快排思想，或者我现在觉得用优先级队列思路也ok。

[31. 下一个排列 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P031_下一个排列.java) ★★★★
* 字节跳动题库。
* 属于思路需要想清楚，写的过程也比较容易犯错的。小细节太多。
* 我和讨论区热门答案的思路一样，但是人家实现的更巧妙简短。











