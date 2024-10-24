package LeetCode.round3;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 241024 medium
 * https://leetcode.com/problems/simplify-path/description/?envType=study-plan-v2&envId=top-interview-150
 * You are given an absolute path for a Unix-style file system, which always begins with a slash '/'.
 * Your task is to transform this absolute path into its simplified canonical path.
 * The rules of a Unix-style file system are as follows:
 * - A single period '.' represents the current directory.
 * - A double period '..' represents the previous/parent directory.
 * - Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
 * - Any sequence of periods that does not match the rules above should be treated as a valid directory or file name.
     For example, '...' and '....' are valid directory or file names.
 *
 * The simplified canonical path should follow these rules:
 * - The path must start with a single slash '/'.
 * - Directories within the path must be separated by exactly one slash '/'.
 * - The path must not end with a slash '/', unless it is the root directory.
 * - The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
 * Return the simplified canonical path.
 *
 * Example 1:
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation:
 * The trailing slash should be removed.
 *
 * Example 2:
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation:
 * Multiple consecutive slashes are replaced by a single one.
 *
 * Example 3:
 * Input: path = "/home/user/Documents/../Pictures"
 * Output: "/home/user/Pictures"
 * Explanation:
 * A double period ".." refers to the directory up a level (the parent directory).
 *
 * Example 4:
 * Input: path = "/../"
 * Output: "/"
 * Explanation:
 * Going one level up from the root directory is not possible.
 *
 * Example 5:
 * Input: path = "/.../a/../b/c/../d/./"
 * Output: "/.../b/d"
 * Explanation:
 * "..." is a valid name for a directory in this problem.
 *
 *
 * Constraints:
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 */
public class P071_SimplifyPath {

    /**
     * Runtime 4 ms Beats 85.84%, Memory 43.08 MB Beats 84.24%
     * 在选择数据结构上出了个错，以及一个边界条件问题。其余整体顺利
     */
    public String simplifyPath(String path) {
        path += "/";    //！！！！！注意：保证以'/'结束，避免丢掉最后一个路径。 如"/home/user/Documents/../Pictures"，不加这个就把最后Pictures丢了。
        int lastSlashIdx = 0;
        List<String> pathList = new LinkedList<>();
        for (int i = 1; i < path.length(); i++) {
            char c = path.charAt(i);
            if(c == '/'){
                String partialPath = path.substring(lastSlashIdx + 1, i);
                lastSlashIdx = i;
                if(partialPath.length() > 0) {
                    handlePartialPath(pathList, partialPath);
                }
            }
        }

        //根据stack，生成结果
        //！！！！！注意生成时，是从前往后，所以这里不适合用stack。一开始用的stack，生成的路径就反了。！！！
        StringBuilder sb = new StringBuilder();
        for(String partialPath: pathList) {
            sb.append("/");
            sb.append(partialPath);
        }
        String res = sb.toString();
        return res.equals("")  ? "/": res;
    }

    private void handlePartialPath(List<String> pathList, String partialPath){
        switch (partialPath){
            case ".":   //当前目录
                //啥也不做
                break;
            case "..":  //上级目录
                //弹栈(栈不空时)
                if(!pathList.isEmpty())
                    pathList.remove(pathList.size() - 1);     //这里模拟栈，移除的是最后一个元素。
                break;
            default:
                pathList.add(partialPath);
                break;
        }
    }

    public static void main(String[] args) {
        P071_SimplifyPath p = new P071_SimplifyPath();
        System.out.println(p.simplifyPath("/home/"));   // "/home"
        System.out.println(p.simplifyPath("/home//foo/"));   // "/home/foo"
        System.out.println(p.simplifyPath("/home/user/Documents/../Pictures"));   // "/home/user/Pictures"
        System.out.println(p.simplifyPath("/../"));   // "/"
        System.out.println(p.simplifyPath("/.../a/../b/c/../d/./"));   // "/.../b/d"

    }
}
