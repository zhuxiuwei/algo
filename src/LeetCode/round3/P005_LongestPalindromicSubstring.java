package LeetCode.round3;

/**
 * 240708
 * # Dynamic Programming，中难度，20年做过
 * Given a string s, return the longest palindromic substring in s.
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 *
 * Constraints:
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 */
public class P005_LongestPalindromicSubstring {
    /**
     * 双指针法。从一个字符串往两边不停探索。
     * AC: 602ms 13%, mem: 81.6%
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s == null)
            return null;
        int maxLeft = -1, maxRight = -2;    //用于记录最长回文串的左右索引
        char[] chats = s.toCharArray();
        for (int i = 0; i < chats.length; i++) {
            for (int j = i; j < chats.length; j++) {
                if(isPalindromic(chats, i, j)){
                    if((j - i) > (maxRight - maxLeft)){
                        maxRight = j;
                        maxLeft = i;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = maxLeft; i <= maxRight ; i++) {
            sb.append(chats[i]);
        }
        return sb.toString();
    }

    /**
     * 判断一个字符数组指定范围内，是否符合回文定义
     * @param charts 字符数组
     * @param start 字符数组指定范围 - 下限
     * @param end 字符数组指定范围 - 上限
     * @return 字符数组指定上下限范围内的字符串，是否符合回文定义
     */
    private boolean isPalindromic(char[] charts, int start, int end){
        int i = start, j = end;
        while (i <= j){
            if(charts[i] == charts[j]){
                i ++;
                j --;
            }else
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        P005_LongestPalindromicSubstring p = new P005_LongestPalindromicSubstring();
        System.out.println(p.longestPalindrome("babad"));   //bab
        System.out.println(p.longestPalindrome("bbbb"));    //bbbb
        System.out.println(p.longestPalindrome("aacdefcaa"));   //aa
        System.out.println(p.longestPalindrome("cbbd"));   //bb
        System.out.println(p.longestPalindrome("baab"));   //baab
        System.out.println(p.longestPalindrome("abaa"));   //aba
        System.out.println(p.longestPalindrome("a"));   //a
        System.out.println(p.longestPalindrome("kyyrjtdplseovzwjkykrjwhxquwxsfsorjiumvxjhjmgeueafubtonhlerrgsgohfosqssmizcuqryqomsipovhhodpfyudtusjhonlqabhxfahfcjqxyckycstcqwxvicwkjeuboerkmjshfgiglceycmycadpnvoeaurqatesivajoqdilynbcihnidbizwkuaoegmytopzdmvvoewvhebqzskseeubnretjgnmyjwwgcooytfojeuzcuyhsznbcaiqpwcyusyyywqmmvqzvvceylnuwcbxybhqpvjumzomnabrjgcfaabqmiotlfojnyuolostmtacbwmwlqdfkbfikusuqtupdwdrjwqmuudbcvtpieiwteqbeyfyqejglmxofdjksqmzeugwvuniaxdrunyunnqpbnfbgqemvamaxuhjbyzqmhalrprhnindrkbopwbwsjeqrmyqipnqvjqzpjalqyfvaavyhytetllzupxjwozdfpmjhjlrnitnjgapzrakcqahaqetwllaaiadalmxgvpawqpgecojxfvcgxsbrldktufdrogkogbltcezflyctklpqrjymqzyzmtlssnavzcquytcskcnjzzrytsvawkavzboncxlhqfiofuohehaygxidxsofhmhzygklliovnwqbwwiiyarxtoihvjkdrzqsnmhdtdlpckuayhtfyirnhkrhbrwkdymjrjklonyggqnxhfvtkqxoicakzsxmgczpwhpkzcntkcwhkdkxvfnjbvjjoumczjyvdgkfukfuldolqnauvoyhoheoqvpwoisniv"));    //qahaq
        System.out.println(p.longestPalindrome("222020221"));   //2202022
    }
}
