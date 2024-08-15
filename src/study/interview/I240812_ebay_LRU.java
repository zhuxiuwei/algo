package study.interview;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * LeetCode 146 medium
 * AC: 51ms Beats 35.17%. Memory 126.72MB Beats 8.37%
 * 请你设计并实现一个满足LRU (最近最少使用) 缓存约束的数据结构。
 * 实现LRUCache类：
 * LRUCache(int capacity)以正整数作为容量capacity初始化 LRU 缓存
 * int get(int key)如果关键字key存在于缓存中，则返回关键字的值，否则返回-1。
 * void put(int key, int value)如果关键字key已经存在，则变更其数据值value；如果不存在，则向缓存中插入该组key-value。如果插入操作导致关键字数量超过capacity，则应该逐出最久未使用的关键字。
 * 函数get和put必须以O(1)的平均时间复杂度运行。
 */
public class I240812_ebay_LRU {

    private int capacity;

    private LinkedHashMap<Integer, Integer> cache;
    public I240812_ebay_LRU(int capacity){
        this.capacity = capacity;
        cache =  new LinkedHashMap<>(capacity, 1, true);
    }

    public int get(int key){
        int res = -1;
        if(cache.containsKey(key)){
            res = cache.get(key);
            cache.remove(key);
            cache.put(key, res);
        }
        return res;
    }

    void put(int key, int value) {
        if(!cache.containsKey(key) && cache.size() >= capacity){    //！！不能少了前面的判断。
            Iterator<Integer> iterator = cache.keySet().iterator();
            int oldestKey = iterator.next();
            cache.remove(oldestKey);
        }
        cache.put(key, value);
    }

    public static void main(String[] args) {
        I240812_ebay_LRU lru = new I240812_ebay_LRU(2);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println(lru.get(1)); //1
        lru.put(3, 3);
        System.out.println(lru.get(2)); //-1
        lru.put(4, 4);
        System.out.println(lru.get(1)); //-1
        System.out.println(lru.get(3)); //3
        System.out.println(lru.get(4)); //4
    }
}
