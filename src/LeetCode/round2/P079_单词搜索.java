package LeetCode.round2;

import study.javase.annotations.PasswordUtils;

import java.util.*;

/**
 200222 Medium
 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

 示例:
 board =
 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]

 给定 word = "ABCCED", 返回 true.
 给定 word = "SEE", 返回 true.
 给定 word = "ABCB", 返回 false.
 */
public class P079_单词搜索 {
    public boolean exist(char[][] board, String word) {
        if(board == null  || board.length < 1)
            return false;
        Map<Character, List<Pair>> map = new HashMap<>();
        Set<Pair> passed = new HashSet<>(); //应该被pass掉的位置
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                List l = map.getOrDefault(board[i][j], new ArrayList<>());
                l.add(new Pair(i, j));
                map.put(board[i][j], l);
            }
        }

        Stack<Pair> curStack = new Stack<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            List<Pair> pairs = map.get(c);
            if(pairs == null)   //字母不存在
                return false;
            boolean found = false;
            for(Pair p: pairs){
                if(i == 0 && !passed.contains(p)) {
                    curStack.push(p);
                    break;
                }
                if(!curStack.isEmpty() && i > 0) {    //判断字母位置和上一个字母是否相邻
                    Pair last = curStack.peek();
                    if (areParisClose(last, p)) {
                        curStack.push(p);
                        found = true;
                        break;
                    }
                }
            }
            if(!found && !curStack.isEmpty()){
                Pair lastPair = curStack.pop();
                passed.add(lastPair);
                passed.removeAll(pairs);    //回退到上一个字母时，需要把当前字母的都从passed去掉。
//                i =
            }
        }
        return true;
    }

    //两个pair是否相邻
    private boolean areParisClose(Pair a, Pair b){
        if(a.i == b.i && Math.abs(a.j - b.j) == 1)
            return true;
        if(a.j == b.j && Math.abs(a.i - b.i) == 1)
            return true;
        return false;
    }

    static class Pair{
        int i;
        int j;
        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return i == pair.i &&
                    j == pair.j;
        }
        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
        @Override
        public String toString() {
            return "Pair{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }

    public static void main(String[] args) {
        P079_单词搜索 p = new P079_单词搜索();
//        System.out.println(p.exist(new char[][]{new char[]{'A','B','C','E'},new char[]{'S','F','C','S'},new char[]{'A','D','E','E'}}, "ABCCED"));   //true
        System.out.println(p.exist(new char[][]{new char[]{'A','B','C','E'},new char[]{'S','F','C','S'},new char[]{'A','D','E','E'}}, "SEE"));      //true
        System.out.println(p.exist(new char[][]{new char[]{'A','B','C','E'},new char[]{'S','F','C','S'},new char[]{'A','D','E','E'}}, "ABCB"));     //false

    }

}
