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

[5. 最长回文子串 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P005_最长回文子串.java) ★★★★★
* solution： https://leetcode.com/articles/longest-palindromic-substring/
* 花了大概20分钟，思路：查找翻转s得到s'，然后找s和s'的最大公共字符串。 第一次还写错了（见longestPalindrome_Wrong），上面solution里说这是个常见错误。
* solution提示可以通过index解决上述错误，改了，比较ugly(用了java原生的indexOf)，通过了，但是还是没AC，因为超时了。
* 和之前九章算法、leetcode时刷过的[P409_LongestPalindrome](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P409_LongestPalindrome.java)的统计回文个数的题目不一样，那个简单很多。

200223 update
* 用中心扩展法完成了。思想：对每一个字母，往左右看。时间复杂度)(n^2)
* 错了好几次！！！！！！
*  1、 主要是左右回看时，回文有两种模式: 1. 所有字母相等，则回看的时候可以只往一个方向； 2. 并非所有字母相等，则回看时必须同时往两个方向。
*  2、 注意利用哨兵，否则处理也很麻烦。

[11. 盛最多水的容器 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P011_盛最多水的容器.java) ★★★★
* 和另一个题目 42.接雨水 https://leetcode.com/problems/trapping-rain-water/ 不完全一样。
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

[141. 环形链表 - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P141_环形链表.java) ★
* 非100题，为阿里题库。
* 不算难。 需要细心点。

[19. 删除链表的倒数第N个节点 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P019_删除链表的倒数第N个节点.java) ★
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

[56.合并区间 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P056_合并区间.java) ★★
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

[215. 数组中的第K个最大元素 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P215_KthLargestElementInAnArray.java)★★
* 字节跳动题库。
* 没做，之前做过。用快排思想，或者我现在觉得用优先级队列思路也ok。

[31. 下一个排列 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P031_下一个排列.java) ★★★★
* 字节跳动题库。
* 属于思路需要想清楚，写的过程也比较容易犯错的。小细节太多。
* 我和讨论区热门答案的思路一样，但是人家实现的更巧妙简短。

[70. 爬楼梯 - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P070_爬楼梯.java) ★★
* 字节跳动题库。
* 使用递归会超时。不用递归快很多，稍微想想就能想出来。其实这也算是个动态规划问题。

[101. 对称二叉树 - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P101_对称二叉树.java) ★★★
* 字节跳动题库。
* 分递归和非递归两种方式。
* 虽然是easy，但是我的思路还是比较傻，参考了最佳答案。
* 关键思路：每个树的右子树都与另一个树的左子树镜像对称，这两个树互为镜像。

[79. 单词搜索](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P079_单词搜索.java) ★★★★
* 字节跳动题库。
* 典型DFS问题。思路不算难，但是写不明白了。 TODO: 完成代码。

[143. 重拍链表 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P143_重排链表.java) ★
* 字节跳动题库。
* 总体顺利。画好图缕清思路就好。 思路：把图画出来，观察到是一个螺旋形。然后观察到用一个双端队列即可以解决。

[54. 螺旋矩阵 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P054_螺旋矩阵.java) ★★
* 字节跳动题库。
* 总体顺利。属于写起来比较容易犯错的。有一个分支写了小bug。

[440. 字典序的第K小数字 - HARD](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/HARD_P440_字典序的第K小数字.java) ★★★★
* 字节跳动题库。
* 我最初思路， 自定义字典序排序的IntStr类，把所有n的数字都变成IntStr类进行字典排序，然后取出k-1位置即可。但是不AC，超过内存限制了，优化了内存依然超过时间限制。
* 看的答案。其实滤清思路，也没那么难。

[25. K个一组翻转链表 - HARD](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/HARD_P025_K个一组翻转链表.java) ★★
* 字节跳动题库。 总体还算顺利，有2个边界值的bug。
* 思路： 先计算链表长度length，然后根据length和k，计算要做几次链表翻转，并进行那么多次。每次翻转后，把相关指针返回。

[199. 二叉树的右视图 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P199_二叉树的右视图.java) ★★
* 字节跳动题库。 总体还算顺利。
* 典型DFS问题。 小马智行问过。我之前也是用二叉树层次遍历做的。用DFS代码更简单。

[93. 复原IP地址 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P093_复原IP地址.java) ★★★★
* 字节跳动题库。
* 典型回溯法问题。 花了很长时间来写。
* 1. 总体框架写的还算顺利，但是返回结果、临时结果的保存，花了不少精力；包括判断字符串能否构成合法的IP地址的一部分（isLengthOk方法），也折腾了一下。
* 2. 开始忘了ip可以有0，以为是1-255，又单独加了0的处理，导致代码很ugly；

[88. 合并两个有序数组 - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P088_合并两个有序数组.java) ★
* 字节跳动题库。
* Easy的，有2个小bug。都和java值传递/引用传递有关系。

[84. 柱状图中的最大矩形 - Hard](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/HARD_P084_柱状图中最大的矩形.java) ★★★
* 因为这个题是85的基础，所以看看。
* 暴力法很快做出来了，好想。
* 栈的方法看的比较迷糊，不看了。

[85. 最大矩形 - Hard](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/HARD_P085_最大矩形.java) ★★★★★
* 字节跳动题库。 小马智行和华为都考了。
* 不会。看的答案。 直接用的暴力法，暴力法AC速度也挺快的。（依赖84的栈方法，和DP，都没看。）
* 暴力法参考：https://leetcode-cn.com/problems/maximal-rectangle/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-1-8/

[1114. 按序打印 - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/P1114_按序打印.java) ★★
* 字节跳动题库。 
* 多线程打印问题。我直接用了volatile特性。

[72. 编辑距离 - Hard](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round2/HARD_P072_编辑距离.java) ★★
* 字节跳动题库。 
* DP问题。直接看的答案https://leetcode-cn.com/problems/edit-distance/solution/zi-di-xiang-shang-he-zi-ding-xiang-xia-by-powcai-3/
 


