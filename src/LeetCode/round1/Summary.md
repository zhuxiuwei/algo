需要注意的的题目总结
=
[Question 371 Sum of Two Integers](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P371_SumofTwoIntegers.java) ★★★★  
`Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.`  
`Example: Given a = 1 and b = 2, return 3.`  
__位操作__的题目，对我比较新，有些意思。一次通过。  

[Question 258 Add Digits](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P258_AddDigits.java#L19) ★  
`Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.`  
`For example:Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.`  
第一次submit失败，因为少了对输入为0时的判断。导致输入0，错误返回9。  

[Question 136 Single Number](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P136_SingleNumber.java) ★★★★  
`Given an array of integers, every element appears twice except for one. Find that single one.`  
`Note:Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?`  
* [第一个的提交](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P136_SingleNumber.java#L53)思路是完全错的，典型的以偏概全。  
* [最优答案](https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round1/easy/P136_SingleNumber.java#L17)：用到了__异或__的性：性质1：a ^ a = 0，性质2：0 ^ a = a。 性质3：交换律。  --->  a ^ a ^ b = b。 不熟，没想到。  

  