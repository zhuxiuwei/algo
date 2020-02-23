package LeetCode.round2;

import java.util.Arrays;

/**
 * 200212 & 200223 Medium
 Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 Example 1:
 Input: "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.

 Example 2:
 Input: "cbbd"
 Output: "bb"
 */
public class P005_最长回文子串 {

    /**
     * 中心扩展法。对每一个字母，往左右看。时间复杂度)(n^2)   200223
     * 执行用时 : 95 ms , 在所有 Java 提交中击败了 28.56% 的用户，内存消耗 : 42.2 MB , 在所有 Java 提交中击败了 11.88% 的用户
     * 开始打算做成DP，没做成。
     * ！！！！！ 错了好几次
     *  1、 主要是左右回看时，回文有两种模式: 1. 所有字母相等，则回看的时候可以只往一个方向； 2. 并非所有字母相等，则回看时必须同时往两个方向。
     *  2、 注意利用哨兵，否则处理也很麻烦。
     */
    public String longestPalindrome(String s) {
        if(s == null || s.isEmpty())
            return "";
        s = "@" + s + "$"; //！！！！！！！！！！！！！！！！！两边是哨兵
        String res = s.charAt(1) + "";  //最少回文包含一个文字。
        for (int i = 1; i < s.length() - 1; i++) {
            String tmp = dp_helper(i, s);
            if(tmp.length() > res.length())
                res = tmp;
        }
        return res;
    }
    private String dp_helper(int i, String s){
        String res = "";
        int l = i - 1, r = i + 1;
        int lOk = i, rOk = i;
        boolean allSameChar = true; //所有回文都是同一个char  ！！！！！！！！！！！！！ 必须用这个变量，来区分两种回文模式！！！！
        while (true){
            if(s.charAt(l) == s.charAt(r)){     //往两边看，如bab模式
                lOk = l;
                rOk = r;
                l --;
                r ++;
                if(allSameChar) allSameChar = s.charAt(lOk) == s.charAt(i);     //！！！！注意必须加前面的if判断，即一旦allSameChar=false，就不用再判断了。
                continue;
            }else if(s.charAt(lOk) == s.charAt(l) && l != lOk && allSameChar){     //往左边看，如bba模式，bb仍构成回文。！！！前提当前回文是所有字符串相等。
                lOk = l;
                l --;
                continue;
            }else if(s.charAt(rOk) == s.charAt(r) && r != rOk && allSameChar){     //往右边看，如abb，bb回文。 ！！！ 前提当前回文是所有字符串相等。
                rOk = r;
                r++;
                continue;
            }
            else
                break;
        }
        if(rOk - lOk + 1 > res.length())
            res = s.substring(lOk, rOk + 1);
        return res;
    }

    // 思路：查找翻转s得到s'，然后找s和s'的最大公共字符串 200212
    // 修复了longestPalindrome_Wrong，结果应该正确，但没有AC，超时了。。。
    public String longestPalindrome_timeout(String s) {
        if(s == null || s.length() <= 1)
            return s;
        StringBuilder sb = new StringBuilder();
        for(int i = s.length() - 1; i>=0; i--){
            sb.append(s.charAt(i));
        }
        String s1 = sb.toString();

        int maxLength = 0;
        String res = "";
        for (int i = 0; i <= s.length() - 1; i++) {     //！！！ bug: 注意两个for循环的边界。bug过。
            for (int j = s.length(); j >= i ; j--) {
                String subStr = s.substring(i, j);
                int subIdx = s1.indexOf(subStr);
                if(subIdx > -1){
                    //添加以下两行，通过index判断，找到的子字符串确实合法的
                    int rightIdx = subIdx + subStr.length() - 1;
                    if( i + rightIdx == s.length() - 1
                            && subStr.length() > maxLength){
                        maxLength = subStr.length();
                        res = subStr;
                    }
                }
            }
        }
        return res;
    }

    //思路：查找翻转s得到s'，然后找s和s'的最大公共字符串
    //错误答案：aacdefcaa，会错误返回aac。因为翻转了也包含aac。
    public String longestPalindrome_Wrong(String s) {
        if(s == null || s.length() <= 1)
            return s;
        StringBuilder sb = new StringBuilder();
        for(int i = s.length() - 1; i>=0; i--){
            sb.append(s.charAt(i));
        }
        String s1 = sb.toString();

        int maxLength = 0;
        String res = "";
        for (int i = 0; i <= s.length() - 1; i++) {     //！！！ bug: 注意两个for循环的边界。错过。
            for (int j = s.length(); j >= i ; j--) {
                String subStr = s.substring(i, j);
                if(s1.contains(subStr)){
                    if(subStr.length() > maxLength){
                        maxLength = subStr.length();
                        res = subStr;
                    }
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        P005_最长回文子串 p = new P005_最长回文子串();
        System.out.println(p.longestPalindrome("babad"));   //bab
        System.out.println(p.longestPalindrome("bbbb"));    //bbbb
        System.out.println(p.longestPalindrome("aacdefcaa"));   //aa
        System.out.println(p.longestPalindrome("cbbd"));   //bb
        System.out.println(p.longestPalindrome("baab"));   //baab
        System.out.println(p.longestPalindrome("abaa"));   //aba
        System.out.println(p.longestPalindrome("a"));   //a
        System.out.println(p.longestPalindrome("kyyrjtdplseovzwjkykrjwhxquwxsfsorjiumvxjhjmgeueafubtonhlerrgsgohfosqssmizcuqryqomsipovhhodpfyudtusjhonlqabhxfahfcjqxyckycstcqwxvicwkjeuboerkmjshfgiglceycmycadpnvoeaurqatesivajoqdilynbcihnidbizwkuaoegmytopzdmvvoewvhebqzskseeubnretjgnmyjwwgcooytfojeuzcuyhsznbcaiqpwcyusyyywqmmvqzvvceylnuwcbxybhqpvjumzomnabrjgcfaabqmiotlfojnyuolostmtacbwmwlqdfkbfikusuqtupdwdrjwqmuudbcvtpieiwteqbeyfyqejglmxofdjksqmzeugwvuniaxdrunyunnqpbnfbgqemvamaxuhjbyzqmhalrprhnindrkbopwbwsjeqrmyqipnqvjqzpjalqyfvaavyhytetllzupxjwozdfpmjhjlrnitnjgapzrakcqahaqetwllaaiadalmxgvpawqpgecojxfvcgxsbrldktufdrogkogbltcezflyctklpqrjymqzyzmtlssnavzcquytcskcnjzzrytsvawkavzboncxlhqfiofuohehaygxidxsofhmhzygklliovnwqbwwiiyarxtoihvjkdrzqsnmhdtdlpckuayhtfyirnhkrhbrwkdymjrjklonyggqnxhfvtkqxoicakzsxmgczpwhpkzcntkcwhkdkxvfnjbvjjoumczjyvdgkfukfuldolqnauvoyhoheoqvpwoisniv"));
        System.out.println(p.longestPalindrome("222020221"));   //2202022

    }
}
