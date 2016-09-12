需要注意的的题目总结
=
[Question 371 Sum of Two Integers](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P371_SumofTwoIntegers.java) ★★★  
`Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.`  
`Example: Given a = 1 and b = 2, return 3.`  
__位操作__的题目，对我比较新，有些意思。一次通过。  

[Question 258 Add Digits](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P258_AddDigits.java#L19) ★  
`Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.`  
`For example:Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.`  
第一次submit失败，因为少了对输入为0时的判断。导致输入0，错误返回9。  

[Question 136 Single Number](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P136_SingleNumber.java) ★★★  
`Given an array of integers, every element appears twice except for one. Find that single one.`  
`Note:Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?`  
* [第一个的提交](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P136_SingleNumber.java#L53)思路是完全错的，典型的以偏概全。  
* [最优答案](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P136_SingleNumber.java#L17)：用到了__异或__的性质：性质1：a ^ a = 0，性质2：0 ^ a = a。 性质3：交换律。  --->  a ^ a ^ b = b。 不熟，没想到。  
习题389.[Find the Difference](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P389_FindTheDifference.java)，可以用一样的解题思路。  

[Question 260 Single Number III](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P260_SingleNumberIII.java) ★★★  
`Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.`  
`For example: Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].?`  
`Note: The order of the result is not important. So in the above example, [5, 3] is also correct. Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?`    
还是异或的bit操作，对于一次全异或后，如何区分两个唯一出现一次数字没有想出好的思路。看的[答案](http://www.cnblogs.com/Anthony-Wang/p/5048762.html)。Bit操作的算法还是不熟练。  

[Question 137 Single Number II](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P137_SingleNumberII.java) ★★★★  
`Given an array of integers, every element appears three times except for one. Find that single one. (注：这个single one只出现一次，不包括2次。)`  
`Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?`  
先实现了[需要constant memory的solution](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P137_SingleNumberII.java)。实现的过程中，注意__两个bug__:
* a. 不要忽略[负数的情况](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P137_SingleNumberII.java#L56)。（这里需要熟悉__[Java中对负数的二进制表示](http://blog.csdn.net/garybrother/article/details/5991918)__。）。同时注意用到了_无符号右移操作_。
* b. 对于(Integer.MIN_VALUE边界值的处理需要注意)[https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/medium/P137_SingleNumberII.java#L38]。  Integer.MAX_VALUE + 1 = Integer.MIN_VALUE。 Integer.MAX_VALUE只用到了31个bit，故表达的数字的绝对值，比Integer.MIN_VALUE是要小1的。