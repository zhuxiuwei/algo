package study.interview.huawei2020;

/**
 * 200312 密码强弱度检查
 * 同时满足以下条件认为密码强：
 * 1. 长度>=6 并且 <= 20
 * 2. 至少包含一个大写，一个小写，一个数字
 * 3. 不能包含三个和以上连续重复字符
 */
public class StrongPasswordChecker {
    /**
     * @param s 密码
     * @return 0表示是强安全，1表示不是强安全。
     */
    public int check(String s){
        int res = -1;
        boolean containLow = false, containHigh = false, containNum= false;
        if(s != null && s.length() >= 6 && s.length() <= 20){
            char[] sc = s.toCharArray();
            for (int i =0; i < sc.length; i++) {
                char c = sc[i];
                if( c >= 'a' && c <= 'z')
                    containLow = true;
                if( c >= 'A' && c <= 'Z')
                    containHigh = true;
                if( c >= '0' && c <= '9')
                    containNum = true;
                if(i > 1){
                    if(c == sc[i - 1] && c == sc[i - 2])
                        return -1;
                }
            }
        }
        if(containLow && containHigh && containNum)
            res = 0;
        return res;
    }

    public static void main(String[] args) {
        StrongPasswordChecker p = new StrongPasswordChecker();
        System.out.println(p.check("a"));   //-1
        System.out.println(p.check("abC123"));  //0
        System.out.println(p.check("abC123aaab"));  //-1
        System.out.println(p.check("abC123aab"));  //0
    }
}
