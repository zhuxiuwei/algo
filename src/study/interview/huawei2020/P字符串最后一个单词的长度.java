package study.interview.huawei2020;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/8c949ea5f36f422594b306a2300315da?tpId=37&tqId=21224&tPage=1&rp=&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 * 200229
 题目描述
 计算字符串最后一个单词的长度，单词以空格隔开。
 输入描述:
 一行字符串，非空，长度小于5000。

 输出描述:
 整数N，最后一个单词的长度。

 示例1
 hello world
 5
 */
public class P字符串最后一个单词的长度 {
    //牛客网练习
    public int strLastLength(String s){
        int res = 0;
        if(s != null && !s.isEmpty()){
            String[] ss = s.split(" ");
            res = ss[ss.length - 1].length();
        }
        return res;
    }

    public static void main(String[] args) {
        //牛客网输入输出必须像下面这样，sb网站。。。
        P字符串最后一个单词的长度 p = new P字符串最后一个单词的长度();
        String s = "";
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            s = sc.nextLine();
        }
        System.out.println(p.strLastLength(s)); //5


        System.out.println(p.strLastLength("hello world")); //5
        System.out.println(p.strLastLength("hello")); //5
        System.out.println(p.strLastLength("hello w ")); //5
        System.out.println(p.strLastLength("XSUWHQ")); //6

    }
}
