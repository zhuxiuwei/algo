题目总结
=
[371 Sum of Two Integers](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P371_SumofTwoIntegers.java) ★★★  
`Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.`  
`Example: Given a = 1 and b = 2, return 3.`  
__位操作__的题目，对我比较新，有些意思。一次通过。  

[258 Add Digits](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P258_AddDigits.java#L19) ★  
`Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.`  
`For example:Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.`  
第一次submit失败，因为少了对输入为0时的判断。导致输入0，错误返回9。  

[136 Single Number](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P136_SingleNumber.java) ★★★  
`Given an array of integers, every element appears twice except for one. Find that single one.`  
`Note:Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?`  
* [第一个的提交](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P136_SingleNumber.java#L53)思路是完全错的，典型的以偏概全。  
* [最优答案](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P136_SingleNumber.java#L17)：用到了__异或__的性质：性质1：a ^ a = 0，性质2：0 ^ a = a。 性质3：交换律。  --->  a ^ a ^ b = b。 不熟，没想到。  
习题389.[Find the Difference](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P389_FindTheDifference.java)，可以用一样的解题思路。  

[260 Single Number III](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P260_SingleNumberIII.java) ★★★  
`Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.`  
`For example: Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].?`  
`Note: The order of the result is not important. So in the above example, [5, 3] is also correct. Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?`    
还是异或的bit操作，对于一次全异或后，如何区分两个唯一出现一次数字没有想出好的思路。看的[答案](http://www.cnblogs.com/Anthony-Wang/p/5048762.html)。Bit操作的算法还是不熟练。  

[137 Single Number II](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P137_SingleNumberII.java) ★★★★  
`Given an array of integers, every element appears three times except for one. Find that single one. (注：这个single one只出现一次，不包括2次。)`  
`Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?`  
先实现了[需要constant memory的solution](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P137_SingleNumberII.java#L14)。实现的过程中，注意__两个bug__:
* a. 不要忽略[负数的情况](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P137_SingleNumberII.java#L56)。（这里需要熟悉__[Java中对负数的二进制表示](http://blog.csdn.net/garybrother/article/details/5991918)__。）。同时注意用到了_无符号右移操作_。
* b. 对于[Integer.MIN_VALUE边界值的处理需要注意](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P137_SingleNumberII.java#L38)。  Integer.MAX_VALUE + 1 = Integer.MIN_VALUE。 Integer.MAX_VALUE只用到了31个bit，故表达的数字的绝对值，比Integer.MIN_VALUE是要小1的。  

[283 Move Zeroes](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P283_MoveZeroes.java) ★  
`Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.`  
`For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].`  
`Note: You must do this in-place without making a copy of the array. Minimize the total number of operations.`  
典型的不需要太思考算法，但是要注意边界值处理等。第一遍没仔细看题，没注意所有非0元素的要保持原来的顺序，错了。修改后一次AC。  

[167 Two SumII](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P167_TwoSumII_InputArrayIsSorted.java) ★★★  
`Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number. The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based. You may assume that each input would have exactly one solution.`  
`Input: numbers={2, 7, 11, 15}, target=9`  
`Output: index1=1, index2=2`  
* [二分搜索](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P167_TwoSumII_InputArrayIsSorted.java#L27)写的还是不能bug free，导致reject了几次。  
* O(n) Runtime的__[双指针算法](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P167_TwoSumII_InputArrayIsSorted.java#L48)__，没有想到。看的指导。其实应该挺直观好想的。  

[382 LinkedList Random Node](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P382_LinkedListRandomNode.java) ★★★  
1. 首先用了[查询length的算法](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P382_LinkedListRandomNode.java#L73)。注意：  
1.1 Random ran = new Random();一定先生成好ran对象，不能放在for里： for(int i = 0; i < new Random().nextInt(length); i++)，这种不能AC;  
1.2  ran.nextInt(length); 要保证length >0，否则报错： java.lang.IllegalArgumentException: bound must be positive  
2. 正规解法是用“__[蓄水池抽样](http://www.cnblogs.com/grandyang/p/5759926.html)__”算法。不知道这个算法，搜索到的。 [自己写的](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P382_LinkedListRandomNode.java#L39)几次都不能AC。还是不太明白原理。不想浪费时间了。  

[237 Delete Node In A LinkedList](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P237_DeleteNodeInALinkedList.java) ★★★  
`delete a node (except the tail) in a singly linked list, given only access to that node`  
思路比较有意思，要开阔思路。  

[383 Ransom Note](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P383_RansomNote.java) ★★  
`Given  an  arbitrary  ransom  note  string  and  another  string  containing  letters from  all  the  magazines,  write  a  function  that  will  return  true  if  the  ransom   note  can  be  constructed  from  the  magazines ;  otherwise,  it  will  return  false. Each  letter  in  the  magazine  string  can  only  be  used  once  in  your  ransom  note.`  
1. [利用Int【32】数组的最佳思路canConstruct()](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P383_RansomNote.java#L23)没想到，注意学习思路 -- __当hash table的key是有限集合时，考虑能不能用数组代替hash table__。  
2. 暴力解法[canConstruct_JavaAPI()](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P383_RansomNote.java#L76)注意bug：replace()方法会替换全部match字符串，要用replaceFirst  

[P100 Same Tree](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P100_SameTree.java) ★  
`Given two binary trees, write a function to check if they are equal or not. Two binary trees are considered equal if they are structurally identical and the nodes have the same value.`  
Note when get pre-order and mid-orde result, [must include string "null"](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P100_SameTree.java#L66). See this scenario:  

As pre=oder, mid-order will both print(1,1), the bug will return "true" wrongly.
