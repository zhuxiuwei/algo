top100 题目总结 2024.7
=
[17. Letter Combinations of a Phone Number - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P017_LetterCombinationsOfPhoneNumber.java) ★
* 第一次做。总体ok。就是在处理中间list结果时，不能直接用两个list赋值方式，也该用拷贝方式。

[22. Generate Parentheses - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P022_GenerateParentheses.java) ★★★
* 做过。第一轮的思路是错的。(#35-#61)

[33. Generate Parentheses - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P033_SearchInRotatedSortedArray.java) ★★★
* 20年做过。二分查找变形。思路比20年差。20年写得很轻松。

[34. Find First and Last Position of Element in Sorted Array - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P034_FindFirstAndLastPositionOfElementInSortedArray.java) ★
* 第一次做。总体ok。二分查找变形。

[94. 中序遍历二叉数Binary Tree Inorder Traversal - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P094_BinaryTreeInorderTraversal.java)) ★★
* 16年做过。第一轮还是忘了需要记录访问过的左节点，会导致不断走回头路，死循环。不过这次的写法比16年的简单点，比16年应该顺利。

[98. 验证二叉搜索数Validate Binary Search Tree - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P098_ValidateBinarySearchTree.java) ★
* 第一次做。总体ok。中序遍历的应用。 有一个小bug要注意。

[05. 最长回文Longest Palindromic Substring - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P005_LongestPalindromicSubstring.java) ★★
* 20年做过。还是没写出来dp解法。不过总体写的貌似比20年简洁。

[62. Unique Paths - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P062_UniquePaths.java) ★★
* 16年做过。自顶向下带备忘录的DP问题。注意公式思路，需要好好想想，一开始没想清楚就写结果是错的。

[55. Jump Game - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P055_JumpGame.java) ★
* 第一次做。总体ok。

[121. Best Time To Buy and Sell Stock - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P121_BestTimeToBuySellStock.java) ★
* 17、20都做过。没难度，这次写的应该比上两次更简洁。【O(n^2)的暴力解法显然不行】

[200. Number Of Islands - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P200_NumberOfIslands.java) ★★★
* 17做过。这次第一轮的代码或思路有点问题，没有AC，代码写的也很ugly。回归17年的思路后，比较顺利。

[001. Two Sum - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P001_TwoSum.java) ★
* 不难，但是每轮做都有通常的错误。

[049. Group Anagrams - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P049_GroupAnagrams.java) ★★
* 不算难，主要是hash的应用。有一个hashcode写法的bug，违背了equals相等，hashcode也应该一样的原则。此外我的代码较长（定义的类多），执行效率也不高

[215. 数组中的第K个最大元素 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P215_数组中的第K个最大元素.java) ★★★
* 16做过。思路不难，主要是快速排序应用。 快排大体结构可以写出来，但是边界处理还是比较费劲，主要是大于 小于 等号的边界条件爱出错！！！！