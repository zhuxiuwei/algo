题目总结
=
[1. Two Sum - easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P001_TwoSum.java) ★★
* Easy的居然还是错了一次。和第一轮错误一样。

[445. Add Two Numbers II - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P445_AddTwoNumbersII.java) ★★★
* 总体还算顺利，循环里的进位问题处理，还是有点乱。 debug了一下。

[2. Add Two Numbers - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P002_AddTwoNumbers.java) ★★
*  总体还算顺利，在ListNode shaobing、ListNode res的关系(29-30行)、保证返回第一个节点这儿花了点时间。

[3. Longest Substring Without Repeating Characters - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P003_LongestSubstringWithoutRepeatingCharacters.java) ★★
*  总体还算顺利，但是写的不快，有俩边界条件bug。

[4. Median of Two Sorted Arrays - Hard](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P004_MedianOfTwoSortedArrays.java) ★★★★★
* 面Contiva时遇到这个题的变种。Contiva是要找到两个sorted array最中间的number。
* TODO: 有了大致思路，还没写。

[5. Longest Palindromic Substring - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P005_LongestPalindromicSubstring.java) ★★★★★
* solution： https://leetcode.com/articles/longest-palindromic-substring/
* 花了大概20分钟，思路：查找翻转s得到s'，然后找s和s'的最大公共字符串。 第一次还写错了（见longestPalindrome_Wrong），上面solution里说这是个常见错误。
* solution提示可以通过index解决上述错误，改了，比较ugly(用了java原生的indexOf)，通过了，但是还是没AC，因为超时了。
* TODO: 有时间看看solution里的动态规划解法。
* 和之前九章算法、leetcide时刷过的[P409_LongestPalindrome](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/P409_LongestPalindrome.java)的统计回文个数的题目不一样，那个简单很多。

[11. Container With Most Water - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P011_ContainerWithMostWater.java) ★★★★
* 和另一个题目 水池蓄水 https://leetcode.com/problems/trapping-rain-water/ 不完全一样。
* 双指针问题，自己写了两个错误方案，一是指针碰撞方案，二是双指针两轮遍历 方案，都不对；
* 最后看了[solution](https://leetcode.com/problems/container-with-most-water/solution/)，还是双指针碰撞，O(n)

