package study.interview.huawei2020;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 200312 归并两个升序list为一个list，时间和空间复杂度都是o(n)
 */
public class MergeList {
    public List<Integer> merge(List<Integer> a, List<Integer> b){
        if(a == null || a.size() == 0)
            return b;
        if(b == null || b.size() == 0)
            return a;
        List<Integer> res = new ArrayList<>();
        Iterator<Integer> itrA = a.iterator(), itrB = b.iterator();
        int intA = itrA.next(), intB = itrB.next();
        while (true){
            if(intA <= intB){
                res.add(intA);
                if(itrA.hasNext())
                    intA = itrA.next();
                else
                    break;
            }else {
                res.add(intB);
                if(itrB.hasNext())
                    intB = itrB.next();
                else
                    break;
            }
        }
        while (itrA.hasNext())
            res.add(itrA.next());
        while (itrB.hasNext())
            res.add(itrB.next());
        return res;
    }
}