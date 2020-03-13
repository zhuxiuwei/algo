package study.interview.huawei2020;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 200229
 https://www.nowcoder.com/practice/a35ce98431874e3a820dbe4b2d0508b1?tpId=37&tqId=21225&tPage=1&rp=&ru=%2Fta%2Fhuawei&qru=%2Fta%2Fhuawei%2Fquestion-ranking
 题目描述
 写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。

 输入描述:
 第一行输入一个有字母和数字以及空格组成的字符串，第二行输入一个字符。

 输出描述:
 输出输入字符串中含有该字符的个数。

 示例1
 输入
 ABCDEF
 A

 输出
 1
 */
public class P计算字符个数 {
    //牛客网练习
    public int count(String words, char c){
        int res = 0;
        for(Character w: words.toCharArray()){
            if(w == c || w == 'a' - 'A' + c  || w == 'A' - 'a' + c)
                res ++;
        }
        return res;
    }

    public static void main(String[] args) {
        P计算字符个数 p = new P计算字符个数();
        Scanner sc = new Scanner(System.in);
        String words = sc.nextLine();
        char c = sc.nextLine().charAt(0);
        System.out.println(p.count(words, c));
    }

}
