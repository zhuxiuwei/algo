package study.algorithm;

/**
 * 	求字符串所有组合 例如输入字符串abc，则输出由字符a、b、c所能排列出来的所有字符串abc、acb、bac、bca、cab和cba。
 * http://shukuiyan.iteye.com/blog/1095637#
 * @date 150325
 */
public class Permutation {  
    public static void permutation(char[]ss,int i){  
        if(ss==null||i<0 ||i>ss.length){  
            return;  
        }  
        if(i==ss.length){  
            System.out.println(new String(ss));  
        }else{  
            for(int j=i;j<ss.length;j++){  
                char temp=ss[j];//交换前缀,使之产生下一个前缀  
                ss[j]=ss[i];  
                ss[i]=temp;  
                permutation(ss,i+1);  
                temp=ss[j]; //将前缀换回来,继续做上一个的前缀排列.  
                ss[j]=ss[i];  
                ss[i]=temp;  
            }  
        }  
    }  
    public static void main(String args[]){  
        char []ss={'a','b','c'};  
        permutation(ss,0);  
    }  
}  