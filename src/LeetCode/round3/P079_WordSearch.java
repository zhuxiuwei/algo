package LeetCode.round3;

/**
 240730 Medium
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
public class P079_WordSearch {
    /**
     * AC: 336ms Beats 9.90%, mem 6.9%
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0)
            return false;
        //初始化访问方向cache
        Direction[][] visitedCache = new Direction[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                visitedCache[i][j] = new Direction();
            }
        }
        for (int i = 0; i < board.length ; i++) {
            for (int j = 0; j < board[i].length; j++) {
                resetDirectionsCache(visitedCache);
                boolean ok = visit(board, i ,j, visitedCache, word);
                if(ok)
                    return true;
            }
        }
        return false;
    }

    private boolean visit(char[][] board, int i, int j, Direction[][] visitedCache, String word){
        visitedCache[i][j].inVisit = true;
        if(board[i][j] != word.charAt(0)) {     //字符串不匹配，返回
            visitedCache[i][j].reset();     //！！！！不能漏，否则探索过程中，回溯后应该再次访问的节点可能不再访问，导致错误结果。
        }else {
            if(word.length() == 1){     //匹配成功
                return true;
            }
            boolean visitedAll = false;
            while (!visitedAll) {
                int nextDirection =visitedCache[i][j].getNextDirection();
                switch (nextDirection) {
                    case -1:    //所有方向都走过了
                        visitedAll = true;
                        visitedCache[i][j].reset();     //！！！！不能漏，否则探索过程中，回溯后应该再次访问的节点可能不再访问，导致错误结果。
                        break;
                    case 1: //上
                        if (i - 1 >= 0 && !visitedCache[i - 1][j].inVisit) {    //！！！！不能漏，否则探索过程中，可能错误访问已经在访问链条中的结果，导致出错
                            visitedCache[i - 1][j].down = true;
                            boolean ok = visit(board, i - 1, j, visitedCache, word.substring(1));
                            if (ok)
                                return true;
                        }
                        break;
                    case 2: //下
                        if (i + 1 < board.length && !visitedCache[i + 1][j].inVisit) {
                            visitedCache[i + 1][j].up = true;
                            boolean ok = visit(board, i + 1, j, visitedCache, word.substring(1));
                            if (ok)
                                return true;
                        }
                        break;
                    case 3: //左
                        if (j - 1 >= 0 && !visitedCache[i][j - 1].inVisit) {
                            visitedCache[i][j - 1].right = true;
                            boolean ok = visit(board, i, j - 1, visitedCache, word.substring(1));
                            if (ok)
                                return true;
                        }
                        break;
                    case 4: //右
                        if (j + 1 < board[i].length && !visitedCache[i][j + 1].inVisit) {
                            visitedCache[i][j + 1].left = true;
                            boolean ok = visit(board, i, j + 1, visitedCache, word.substring(1));
                            if (ok)
                                return true;
                        }
                        break;
                }
            }
        }
        return false;
    }

    private void resetDirectionsCache(Direction[][] visitedCache){
        for (int i = 0; i < visitedCache.length; i++) {
            for (int j = 0; j < visitedCache[i].length; j++) {
                visitedCache[i][j].reset();
            }
        }
    }

    //记录一个节点，四个方向是否访问过
    static class Direction{
        boolean up;
        boolean down;
        boolean left;
        boolean right;
        boolean inVisit;    //正在访问链中
        /**
         * 获取下一个可以移动的位置。
         * -1： 已经都访问过
         */
        int getNextDirection(){
            if(!up){
                up = true;
                return 1;
            }
            if(!down){
                down = true;
                return 2;
            }
            if(!left){
                left = true;
                return 3;
            }
            if(!right){
                right = true;
                return 4;
            }
            return -1;
        }
        void reset(){
            up = false;
            down = false;
            left = false;
            right = false;
            inVisit = false;
        }
    }

    public static void main(String[] args) {
        P079_WordSearch p = new P079_WordSearch();
        char[][] board = new char[][]{new char[]{'A','B','C','E'},new char[]{'S','F','C','S'},new char[]{'A','D','E','E'}};
        System.out.println(p.exist(board, "ABCCED"));   //true
        System.out.println(p.exist(board, "SEE"));      //true
        System.out.println(p.exist(board, "ABCB"));     //false

        board = new char[][]{new char[]{'A','B','C','E'},new char[]{'S','F','E','S'},new char[]{'A','D','E','E'}};
        System.out.println(p.exist(board, "ABCESEEEFS"));     //true    -- 这个case妙，能发现深层次bug
    }
}
