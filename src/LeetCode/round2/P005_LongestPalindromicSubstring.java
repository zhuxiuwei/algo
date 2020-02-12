package LeetCode.round2;

import sun.font.StandardGlyphVector;

/**
 * 200212 Medium
 Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 Example 1:
 Input: "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.

 Example 2:
 Input: "cbbd"
 Output: "bb"
 */
public class P005_LongestPalindromicSubstring {


    // 思路：查找翻转s得到s'，然后找s和s'的最大公共字符串
    // 修复了longestPalindrome_Wrong，结果应该正确，但没有AC，超时了。。。
    public String longestPalindrome(String s) {
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
        P005_LongestPalindromicSubstring p = new P005_LongestPalindromicSubstring();
        System.out.println(p.longestPalindrome("babad"));   //bab
        System.out.println(p.longestPalindrome("bbbb"));    //bbbb
        System.out.println(p.longestPalindrome("aacdefcaa"));   //aa
        System.out.println(p.longestPalindrome("kyyrjtdplseovzwjkykrjwhxquwxsfsorjiumvxjhjmgeueafubtonhlerrgsgohfosqssmizcuqryqomsipovhhodpfyudtusjhonlqabhxfahfcjqxyckycstcqwxvicwkjeuboerkmjshfgiglceycmycadpnvoeaurqatesivajoqdilynbcihnidbizwkuaoegmytopzdmvvoewvhebqzskseeubnretjgnmyjwwgcooytfojeuzcuyhsznbcaiqpwcyusyyywqmmvqzvvceylnuwcbxybhqpvjumzomnabrjgcfaabqmiotlfojnyuolostmtacbwmwlqdfkbfikusuqtupdwdrjwqmuudbcvtpieiwteqbeyfyqejglmxofdjksqmzeugwvuniaxdrunyunnqpbnfbgqemvamaxuhjbyzqmhalrprhnindrkbopwbwsjeqrmyqipnqvjqzpjalqyfvaavyhytetllzupxjwozdfpmjhjlrnitnjgapzrakcqahaqetwllaaiadalmxgvpawqpgecojxfvcgxsbrldktufdrogkogbltcezflyctklpqrjymqzyzmtlssnavzcquytcskcnjzzrytsvawkavzboncxlhqfiofuohehaygxidxsofhmhzygklliovnwqbwwiiyarxtoihvjkdrzqsnmhdtdlpckuayhtfyirnhkrhbrwkdymjrjklonyggqnxhfvtkqxoicakzsxmgczpwhpkzcntkcwhkdkxvfnjbvjjoumczjyvdgkfukfuldolqnauvoyhoheoqvpwoisniv"));
    }
}
