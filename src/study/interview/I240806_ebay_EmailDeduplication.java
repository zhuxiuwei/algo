package study.interview;

import java.util.*;

/**
 * 邮件数组去重。输入一个二位数组，里面包含了姓名和邮件，第一个位置是姓名，后面是邮件地址。如果有邮件地址重复，就合并。
 * 例子：
 * // Input = [
 *      ["John", "johnsmith@mail.com", "john00@mail.com"],
 *      ["John", "johnnybravo@mail.com"],
 *      ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
 *      ["Mary", "mary@mail.com"]
 *    ]
 *    其中，第一个和第三个组有重复：johnsmith@mail.com，可以合并，所以结果是：
 * // Output = [
 *      ["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
 *      ["John", "johnnybravo@mail.com"],
 *      ["Mary", "mary@mail.com"]
 *    ]
 */
public class I240806_ebay_EmailDeduplication {

    /**
     * 1、先判断每个邮件地址，在哪个组出现过
     * 2、然后根据这个信息，生成哪些组应该合并的信息
     * 3、根据组的合并信息，生成最终结果
     * 代码比较ugly。需要更好的思路。
     */
    public String[][] handle(String[][] emails) {
        String[][] res = new String[emails.length][];

        if(emails == null || emails.length == 0){
            return res;
        }

        //1、找到每个邮件在哪个组出现过。
        Map<String, Set<Integer>> dedup = new HashMap<>();  //k: 邮件，v: 出现的数组下标
        for (int i =0; i < emails.length; i++){
            for (int j = 1; j < emails[i].length; j++) {
                Set<Integer> indexes = dedup.getOrDefault(emails[i][j], new HashSet<>());
                indexes.add(i);
                dedup.put(emails[i][j], indexes);
            }
        }
//        System.out.println(dedup);
        //2、 获取可merge的组的信息
        Set<Set<Integer>> mergedIndexes = getMergeIndexes(dedup);
        //3、 根据可合并组信息，返回合并后的邮件
        return regroupMails(mergedIndexes, emails);
    }

    //获取可merge的组的信息
    private Set<Set<Integer>> getMergeIndexes(Map<String, Set<Integer>> dedup ){
        Set<Set<Integer>> mergedValues = new HashSet<>();
        for(Set<Integer> values: dedup.values()){
            if(mergedValues.isEmpty()){
                mergedValues.add(values);
            }else {
                boolean hasIntersection = false;
                for (Iterator<Set<Integer>> iterator = mergedValues.iterator(); iterator.hasNext(); ) {
                    Set<Integer> mergedSet = iterator.next();
                    if (hasIntersection(values, mergedSet)) {
                        // 存在合并逻辑
                        mergedSet.addAll(values);
                        hasIntersection = true;
                        break;
                    }
                }
                if (!hasIntersection) {
                    // 不存在合并逻辑，把当前values放入集合
                    mergedValues.add(values);
                }
            }
        }
//        System.out.println(mergedValues);   // 所有可合并的组
        return mergedValues;
    }

    /**
     * 根据可合并组信息，返回合并后的邮件
     * @param mergedIndexes 可合并的组下标集合
     * @return 合并后的邮件二维数组
     */
    private String[][] regroupMails(Set<Set<Integer>> mergedIndexes, String[][] emails){
        String[][] res = new String[mergedIndexes.size()][];
        int idx = 0;
        for (Set<Integer> mergedIndex: mergedIndexes){
            List<String> mergedValues = new ArrayList<>();
            for(Integer index: mergedIndex) {
                for(String email: emails[index]) {
                    if(!mergedValues.contains(email)){
                        mergedValues.add(email);
                    }
                }
            }
            res[idx++] = mergedValues.toArray(new String[0]);
        }
        return res;
    }

    private boolean hasIntersection(Set<Integer> s1, Set<Integer> s2){
        if(s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty())
            return false;
        for (int s: s1){
            if(s2.contains(s))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        I240806_ebay_EmailDeduplication q = new I240806_ebay_EmailDeduplication();
        System.out.println(Arrays.deepToString(
                q.handle(new String[][]{
                        {"John", "johnsmith@mail.com", "john00@mail.com"},
                        {"John", "johnnybravo@mail.com"},
                        {"John", "johnsmith@mail.com", "john_newyork@mail.com"},
                        {"Mary", "mary@mail.com"}
                })));
                //[[John, johnnybravo@mail.com], [John, johnsmith@mail.com, john00@mail.com, john_newyork@mail.com], [Mary, mary@mail.com]]
    }
}
