九章算法的练习。  

## [新鲜出炉，Amazon SDE 面经(电面+Onsite)](http://www.jiuzhang.com/qa/3896/)  
[翻转字符串](https://github.com/zhuxiuwei/algo/blob/master/src/lintcode/round1/P053_ReverseWordsInAString.java)  
  
[划分数组](https://github.com/zhuxiuwei/algo/blob/master/src/lintcode/round1/P031_PartitionArray.java)  
注意**一个bug**。  

[链表求和。要求O（n）的时间复杂度。](https://github.com/zhuxiuwei/algo/blob/master/src/lintcode/round1/P167_AddTwoNumbers.java).  
很简单的题，结果弄错了好几次。状态不太好。  

[二叉树两个节点的最近公共祖先](https://github.com/zhuxiuwei/algo/blob/master/src/lintcode/round1/P088_LowestCommonAncestor.java).  
还是要注意如何退出递归调用问题、**有状态的类可能引入bug**的问题。  

[在一个文件中，找出所有的Anagram](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P438_FindAllAnagramsInAString.java) ★★★★★  
我的[原始方法](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P438_FindAllAnagramsInAString.java#L81)超时了。  
可以用**滑动窗口**，这也是双指针法的一种。 自己开始想不出来。注意这种思路。  

[Find smallest range containing elements from k lists](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/hard/P340_LongestSubstringWithAtMostKDistinctCharacters.java) ★★★  
LeetCode类似题目是第340题，hard。也和上一题目类似，利用**滑动窗口算法**来解决。注意写的时候还是有bug。  

[实现一个类似于并查集的数据结构，可以合并一些点的集合，并且查询点所在集合的点个数等等]  
见算法导论第21章，用户不相交集合的数据结构。  

[Finding max and 2nd max in an array by minimum times.](https://github.com/zhuxiuwei/algo/blob/master/src/study/interview/jiuzhang/FindMax2ndMaxInArray.java)。  
算法导论做过这个题目。感觉有点**分治思想**在里面。  

[逆序对](https://github.com/zhuxiuwei/algo/blob/master/src/lintcode/round1/P532_ReversePairs.java) ★★★  
典型**分制**算法。算法导论第二章里也有[这个题目](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap02_GettingStarted/Thinks24_Inversion_NiXuDui.java)。注意**哨兵的使用**，**和一个bug.**写的不是太顺畅，merge sort写法有点忘了。  

[设计一个LRU](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/hard/P146_LRUCache.java) ★★★  
**提交错了4次**。属于本身逻辑不算复杂，但是实现起来步骤多、繁琐，容易出错的问题。有两个注意点。  

## [Amazon HackerRank OA 面经](http://www.jiuzhang.com/qa/748/)  
题目1：Find first repeating letter in a string. 比如输入“abcba”, 返回“a”。 [代码](https://github.com/zhuxiuwei/algo/blob/master/src/study/interview/jiuzhang/FindFirstRepeatingLetterInAString.java)  

题目2：Merge 2 arrays in 1 array. 两个sorted array都有M个元素，但是a的capacity是M， b的capacity是2M，最后是把a中的元素加入到b中，保持sorted。[代码](https://github.com/zhuxiuwei/algo/blob/master/src/study/interview/jiuzhang/Merge2ArraysInto1Array.java)  
感觉解决方法不是很优雅。虽然可以做到，每个元素只移动一次。  

题目3：Stock Maximize https://www.hackerrank.com/challenges/stockmax.  
[代码](https://github.com/zhuxiuwei/algo/blob/master/src/study/interview/jiuzhang/StockMax.java)。和LeetCode上几个Stock Max题目都不一样（包括第二题）。  

## [Amazon Sydney 群面 09/2015](http://www.jiuzhang.com/qa/1009/)  
题目2. merge K iterator. 需要实现hasNext 和 next。 ★★  
[LeetCode](https://leetcode.com/problems/merge-k-sorted-lists/#/description)上的**Hard**题目。[代码](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/hard/P023_MergeKSortedLists.java)。有两个注意点。  

题目3. 设计红绿灯。★★★★  
没做。参看：[黑马程序员_关于交通灯管理系统的设计思路](http://blog.csdn.net/jijinhui1986/article/details/17844351)  

题目4。最简单的single number。  
[LeetCode](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P136_SingleNumber.java)做过了。略过。  

## [Amazon 面经(OA+Onsite)](http://www.jiuzhang.com/qa/2623/)
[Trap Water](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/hard/P042_TrappingRainWater.java) ★★★★★  
**REFER**  
典型**对撞型双指针**问题。当年google面过，就没答上来。[我自己的思路](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/hard/P042_TrappingRainWater.java#L47)错了，而且写起来很复杂。参考的[这个方案](https://discuss.leetcode.com/topic/3016/share-my-short-solution)。  
看了答案后，[自己又实现了一遍](https://github.com/zhuxiuwei/algo/blob/master/src/study/interview/jiuzhang/TrapWater1.java)。  

 
