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

[94. 中序遍历二叉数Binary Tree Inorder Traversal - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P094_BinaryTreeInorderTraversal.java)) ★★
* 16年做过。第一轮还是忘了需要记录访问过的左节点，会导致不断走回头路，死循环。不过这次的写法比16年的简单点，比16年应该顺利。

[98. 验证二叉搜索数Validate Binary Search Tree - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P098_ValidateBinarySearchTree.java) ★
* 第一次做。总体ok。中序遍历的应用。 有一个小bug要注意。

[05. 最长回文Longest Palindromic Substring - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P005_LongestPalindromicSubstring.java) ★★
* 20年做过。还是没写出来dp解法。不过总体写的貌似比20年简洁。

[62. Unique Paths - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P062_UniquePaths.java) ★★
* 16年做过。自顶向下带备忘录的DP问题。注意公式思路，需要好好想想，一开始没想清楚就写结果是错的。

[55. Jump Game - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P055_JumpGame.java) ★
* 第一次做。总体ok。

[121. Best Time To Buy and Sell Stock - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P121_BestTimeToBuySellStock.java) ★
* 17、20都做过。没难度，这次写的应该比上两次更简洁。【O(n^2)的暴力解法显然不行】

[200. Number Of Islands - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P200_NumberOfIslands.java) ★★★
* 17做过。这次第一轮的代码或思路有点问题，没有AC，代码写的也很ugly。回归17年的思路后，比较顺利。

[001. Two Sum - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P001_TwoSum.java) ★
* 不难，但是每轮做都有通常的错误。

[049. Group Anagrams - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P049_GroupAnagrams.java) ★★
* 不算难，主要是hash的应用。有一个hashcode写法的bug，违背了equals相等，hashcode也应该一样的原则。此外我的代码较长（定义的类多），执行效率也不高

[215. 数组中的第K个最大元素 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P215_数组中的第K个最大元素.java) ★★★
* 16做过。思路不难，主要是快速排序应用。 快排大体结构可以写出来，但是边界处理还是比较费劲，主要是大于 小于 等号的边界条件爱出错！！！！

[19. 删除链表的倒数第N个节点 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P019_RemoveNthNodeFromEndOfList.java) ★
* 20做过，一次过。这次也是一次过。不过思路和20年完全不同，20年的思路更巧妙，代码更简洁。

[48. 矩阵旋转 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P048_RotateImage.java) ★★★
* 第一次做。旋转位置公式推导很简单，麻烦的是**在旋转过程中，旧值总要去覆盖新位置的值，那么新位置的值如何缓存下来**以便后续旋转到它的时候正确使用。
* 一开始，我以为让它从(0,0)开始循环n*n次，就可以把所有数字旋转一遍了，实则不是，这样只能旋转到(0,0)的对应接下来的4个角的值。
* 所以我加了一个二维数组，复制原始matrix所有值，用作旧值的cache。这么做符合【You have to rotate the image in-place】要求，但不知道怎么做是否破坏了【DO NOT allocate another 2D matrix and do the rotation.】的要求。

[24. Swap Nodes In Pairs 联表元素两两反转 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P024_SwapNodesInPairs.java) ★★
* 第一次做。链表翻转升级版。还是有debug发现会出错的地方，需要本地debug后一次过。

[78. Sub sets - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P078_Subsets.java) ★
* 第一次做。backtracking问题，总体比较顺利。关键是想明白全部解生成的步骤。

[155. Min Stack - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P155_MinStack.java) ★
* 第一次做。主要是思路，编程不难。有一个边界小问题。

[240. Search a 2D Matrix II - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P240_SearchA2DMatrixII.java) ★
* 第一次做。感觉我做的方法比较暴力，但是AC速度还行。二分查找写的还是有点小问题(while循环上)

[21. Merge Two Sorted Lists - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P021_MergeTwoSortedLists.java) ★★★
* 头两轮做过。虽然是easy，还是费了功夫： 第一次思路完全是错的。第二轮一次改对了。
* 看以前，16年做的最顺，20年也错了一次但是思路没那么离谱， 这次第一轮做错思路就错的离谱。

[11. 盛最多水的容器 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P011_ContainerWithMostWater.java) ★★★★
* 20做过。和另一个题目 [42.接雨水](https://leetcode.com/problems/trapping-rain-water/) 不一样。
* 双指针问题，一开始O(n^2)的暴力法会超时，然后自己想了个思路不对（20年一样），最后还是抄的标准答案思路。 

[42. 接雨水 - Hard](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/HARD_P042_TrapRainWalter.java) ★★★★★
* 头两轮做过。经典hard。
* 还是不会。先自己写了个o(n^2)写法，发现不对，想简单了。然后参考了别人的双数组思路（没用双指针），主要是思路难想，写不困难。

[3. Longest Substring Without Repeating Characters - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P003_LongestSubstringWithoutRepeatingCharacters.java) ★
* 20做过。不难。有个小bug。

[238. Move Zeroes - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P283_MoveZeroes.java) ★
* 16做过。没难度，写的应该比16简洁。

[31. Next Permutation - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P031_NextPermutation.java) ★★★★
* 第一次做。主要是搞明白数字交换规则的思路花了很多时间，错了2-3轮。有个思路，跑一堆case后发现有fail，就得又重新整理思路，直到找到正确思路。

[75. Sort Colors - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P031_NextPermutation.java) ★★
* 第一次做。不难，但是写的时候也不能bug free，调试着写的。

[46. Permutations 全排列 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P046_全排列.java) ★★★
* 16做过。
* 第一次代码[写的很ugly，重复太多](https://github.com/zhuxiuwei/algo/commit/ac6ca48a68a48fff5ea6cb982f6ec3da711e4202)。 
* 第二天[重构了下](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P046_%E5%85%A8%E6%8E%92%E5%88%97.java)，好了很多，但是**表示还不够熟**。
* TODO: backtracking问题需要统一看下。

[189. Rotate Array数组移动k位 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P189_RotateArray.java) ★★
* 第一次做。 符合了题目要求的最优解，但是解决「避免移动顺序形成环，导致死循环」问题花了一些时间，导致写代码时间较长。另外涉及到求是否存在公约数算法感觉实现的效率较低。

[118. Pascal's Triangle - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P118_PascalTriangle.java) ★
* 第一次做。简单，有一个小bug。

[79. Word Search矩阵中单词搜索 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P079_WordSearch.java) ★★★
* 典型回溯问题。20做过，上次没写完代码。这次思路不难，但是在判断一个节点是否被访问过的过程，不断出错需要debug才能写出来【看代码，大致三处错误】，最后写代码耗时很长。

[739. Daily Temperatures - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P739_DailyTemperatures.java) ★★★★
* 第一次做。只想到了暴力O(n^2)法，用栈的方法没想出来，看了下别人思路。

[226. Invert Binary Tree - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P226_InvertBinaryTree.java) ★
* 第2次做。没啥难度。

[45. Jump Game II - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P045_JumpGameII.java) ★★★
* 第一次做。一开始按照和Jump Game 1一样的思路，思路是错的。后来想出了新思路，写的时候有点小Bug。

[101. Symmetric Tree - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P101_SymmetricTree.java) ★★
* 20做过。 有递归、迭代2种写法。
* 迭代写法，比20顺利，debug时发现一个bug。
* 递归写法，一开始也和20一样是个蠢思路。看20笔记，发现思路不对后，重新考虑思路，相对顺利，提交时发现一个bug。

[139. Word Break - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P139_WordBreak.java) ★★★
* 第一次做。 DP，自顶向下带备忘录法。
* 思路还行，但是写的时候问题挺多的。
* 一个是helper里的循环条件容易出错
* 二是第一次写的时候，根本没有正确使用cache，实际在不停重复计算同样的子空间，导致大test case超时。
* **一个教训：** 注意cache只初始化明确true的部分。不明确的部分不要默认给false，而是保持默认为null。否则后续用cache时，不知道false结果是否可信(是初始值，还是计算结果？)。从而不停重复计算，没用上cache。

[543. Diameter of Binary Tree - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P543_DiameterOfBinaryTree.java) ★
* 第一次做，基本顺利，但是提交不是bug free。

[994. Rotting Oranges - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P994_RottingOranges.java) ★★
* 第一次做。图遍历问题，总体顺利，有个边界值小问题。

[236. Lowest Common Ancestor of a Binary Tree二叉树最近公共祖先 - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P236_LowestCommonAncestorOfBinaryTree.java) ★★
* 20做过。这次用的BFS层次遍历，代码较长，运行效率也不高，但是写起来比较顺利。
* 20年的DFS写法简洁很多。

[160. Intersection of Two Linked Lists相交链表 - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P160_IntersectionOfTwoLinkedLists.java) ★
* 20做过。没啥难度顺利。

[74. Search a 2D Matrix - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P074_Search2DMatrix.java) ★
* 没做过。二分查找变形。思路和代码顺利，有一个小Bug。

[136. Single Number - Easy](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P136_SingleNumber.java) ★
* 16做过。看到了16年的实现思路，就没难度了。关键是思路。

[148. Sort List - Medium](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P148_SortList.java) ★★★★★
* 没做过。先熟悉了经典的基于数组的merge sort。
* ！！！ 思路倒是不是很难，但写起来费老鼻子劲了。完全一步步debug写的。写代码花了很长时间(大半天)，而且代码很长。

[51. N皇后问题 - Hard](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/HARD_P051_NQueens.java) ★★★
* 以前写过，参考了以前的代码。自己从头写估计还是费劲。