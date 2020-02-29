package study.interview.huawei2020;

import sun.nio.ch.sctp.SctpNet;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

//华为200229 笔试题
public class P水果摊 {
    /**
     * @param m 成本价
     * @param n 卖出价
     * @param k 本金
     * @return 最后手里的钱
     */
    public int price(int[] m, int[] n, int k){
        int[][] merged = new int[m.length][2];
        for (int i = 0; i < m.length; i++) {
            merged[i][0] = m[i];
            merged[i][1] = n[i];
        }
        quickSort(merged, 0, n.length - 1);
        for (int i = 0; i < merged.length; i++) {
            if(merged[i][1] > merged[i][0] && k > merged[i][0])
                k += merged[i][1] - merged[i][0];

        }
        return k;
    }

    //按照成本价排序
    public void quickSort(int[][] arr, int start, int end){
        int[] anchor = arr[start];
        int i = start, j = end;
        while (i < j){
            while (i < j && arr[j][0] >= anchor[0])
                j --;
            arr[i] = arr[j];

            while (i < j && arr[i][0] <= anchor[0])
                i ++;
            arr[j] = arr[i];
        }
        arr[i] = anchor;
        if(start < i - 1)
            quickSort(arr, start, i  - 1);
        if(end > i + 1)
            quickSort(arr, i  + 1, end);
    }

    public static void main(String[] args) {
        P水果摊 p = new P水果摊();
        System.out.println(p.price(new int[]{4,2,6,4}, new int[]{5,3,8,7}, 15));    //22


        //牛客网必须要求的输入输出格式
//        Scanner sc = new Scanner(System.in);
//        String[] l1 = sc.nextLine().split(",");
//        String[] l2 = sc.nextLine().split(",");
//        int[] m = new int[l1.length];
//        int[] n = new int[l2.length];
//        for (int i = 0; i < l1.length; i++)
//            m[i] = Integer.parseInt(l1[i]);
//        for (int i = 0; i < l1.length; i++)
//            n[i] = Integer.parseInt(l2[i]);
//        int k = Integer.parseInt(sc.nextLine());
//        System.out.println(p.price(m, n, k));
    }
}
