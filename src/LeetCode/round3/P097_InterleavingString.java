package LeetCode.round3;

public class P097_InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        return false;
    }

    public static void main(String[] args) {
        P097_InterleavingString p = new P097_InterleavingString();
        /**
         * true
         * Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
         * Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
         */
        System.out.println(p.isInterleave("aabcc", "dbbca", "aadbbcbcac"));

        System.out.println(p.isInterleave("aabcc", "dbbca", "aadbbbaccc"));     //false
        System.out.println(p.isInterleave("", "", ""));     //true

    }
}
