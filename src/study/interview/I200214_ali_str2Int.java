package study.interview;

public class I200214_ali_str2Int {

    // str 2 int，不考虑int溢出。
    public int strToInt(String s){
        int res = 0;
        if(s == null || s.trim().equals(""))
            return 0;
        s = s.trim();
        boolean isNegative = false;
        if(s.startsWith("-")) {
            isNegative = true;
            s = s.substring(1, s.length());
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int num = (1 - (int)'1') + (int)c;  //！！！！！ bug 前面的减法我第一次写反了！！！！
            res += Math.pow(10, s.length() - i - 1) * num;
        }

        return isNegative ? -res: res;
    }

    public static void main(String[] args) {
        I200214_ali_str2Int t = new I200214_ali_str2Int();
        System.out.println(t.strToInt("-5432445"));
    }
}
