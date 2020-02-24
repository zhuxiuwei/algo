package LeetCode.round2;

import java.util.*;

public class P093_复原IP地址 {

    /**
     * 200223 Medium
     * 典型回溯法问题。
     * 执行用时 : 16 ms , 在所有 Java 提交中击败了 5.55% 的用户。 内存消耗 : 39.6 MB , 在所有 Java 提交中击败了 5.05% 的用户
     * ！！！！！！！！！！！！总体花费了很长时间！ 回溯法我还是写起来不熟！
     * 1. 总体框架写的还算顺利，但是返回结果、临时结果的保存，花了不少精力；包括判断字符串能否构成合法的IP地址的一部分（isLengthOk方法），也折腾了一下。
     * 2. 开始忘了ip可以有0，以为是1-255，又单独加了0的处理，代码很uglyl；
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if(s == null || s.isEmpty())
            return res;
        Map<String , Set<String>> tmp = new HashMap<>();
        helper(s, 0, 1,4, tmp);
        Set<String> set = tmp.getOrDefault( 0 + "," + 1 + "," + 4, null);   //如何保存每一步的临时结果，折腾半天。
        if(set != null) {
            for (String ip : set) {
                boolean isOk = true;
                String[] parts = ip.split("\\.");
                for (String part : parts) {
                    if (part.startsWith("0") && !part.equals("0")) {        //！！！！最开始没想到0的问题，忘了ip可以包含0.这里是修复00、001这种错误的结果。
                        isOk = false;
                        break;
                    }
                }
                if (isOk)
                    res.add(ip);
            }
        }
        return res;
    }
    private void helper(String s, int left, int right, int num, Map<String , Set<String>> tmp){
        if(!isLengthOk(s, left, num))    //字符串长度不可能构成IP地址
            return;
        String key = left + "," + right + "," + num;
        if(num == 1){
            Set<String> set = tmp.getOrDefault(key, new HashSet<>());
            int n = Integer.parseInt(s.substring(left));
            if (n >= 0 && n <= 255)
                set.add(s.substring(left));
            tmp.put(key, set);
        }else {
            while (true) {
                String sub = s.substring(left, right);
                int n = Integer.parseInt(sub);
                if (n >= 0 && n <= 255) {
                    if (isLengthOk(s, right, num - 1 )){  //右边的仍能构成合法IP地址
                        helper(s, right, right + 1, num - 1, tmp);
                        Set<String> lastRes = tmp.get(right + "," + (right + 1) + "," + (num - 1));   //上一轮结果
                        if(lastRes != null && lastRes.size() > 0) {
                            Set<String> set = tmp.getOrDefault(key, new HashSet<String>());
                            for (String partIp : lastRes)
                                set.add(sub + "." + partIp);
                            tmp.put(key, set);
                        }
                    }
                } else
                    break;
                right++;
                if(right > s.length())
                    break;
            }
        }
    }
    //通过长度，判断字符串是否能够构成一个合法IP地址的一部分。
    //s: 字符串， start: 开始位置， num: ip段位，ipv4共4段，从左到右分别是1-4.
    private boolean isLengthOk(String s, int start, int num){
        int minLength = num, maxLength = num * 3, length = s.length() - start;
        boolean res = length >= minLength && length <= maxLength;
        return res;
    }

    public static void main(String[] args) {
        P093_复原IP地址 p = new P093_复原IP地址();
        System.out.println(p.restoreIpAddresses("25525511135"));    //[255.255.11.135, 255.255.111.35]
        System.out.println(p.restoreIpAddresses("11112"));
        System.out.println(p.restoreIpAddresses("0000"));    //[0.0.0.0]
        System.out.println(p.restoreIpAddresses("010010"));    //["0.10.0.10","0.100.1.0"]
        System.out.println(p.restoreIpAddresses(""));    //[]
        System.out.println(p.restoreIpAddresses("0"));    //[]

    }
}
