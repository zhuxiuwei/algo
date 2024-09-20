package LeetCode.round3;

import java.util.HashMap;
import java.util.Map;

/**
 * 240920 medium
 *
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of
 * its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers
 * in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 *
 * Example 1:
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * Example 2:
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 *
 * Example 3:
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 *
 * 0 <= n <= 1000
 * -10000 <= Node.val <= 10000
 * Node.random is null or is pointing to some node in the linked list.
 */
public class P138_CopyListWithRandomPointer {
    /**
     * AC: Runtime 1 ms Beats 7.98%, Memory 44.66 MB Beats 19.99%
     * 总体思路：主要难点是random指针的处理，采用俩map来记录这个信息。
     * 总体顺利。有一个bug。
     */
    public Node copyRandomList(Node head) {
        Node node = head;

        Map<Node, Integer> oldNodeToIdxMap = new HashMap<>();   //保存old node -> idx的map
        Map<Integer, Node> newIdxToNodeMap = new HashMap<>();   //保存idx -> new node的map

        //第一轮，fill oldIdxToNodeMap。
        //并创建new node，让new node的next指针指向正确位置
        Node last = null;
        for (int i = 0; node != null; i++) {
            oldNodeToIdxMap.put(node, i);
            //处理新节点及其上游节点的next指针
            Node newNode = new Node(node.val);
            newIdxToNodeMap.put(i, newNode);
            if(last != null){
                last.next = newNode;
            }
            last = newNode;
            node = node.next;
        }

        //第二轮：处理newNode的random指针
        node = head;
        for (int idx = 0; node != null; idx++) {
            Node ranNode = node.random;
            if(ranNode != null) {       //！！！！这个不能少，否则会NPE
                //获取newNode
                Node newNode = newIdxToNodeMap.get(idx);

                //获取ranNode的idx
                int ranNodeIdx = oldNodeToIdxMap.get(ranNode);

                //获取newNode对应的new ranNode，并让newNode的random指针指向它。
                Node ranNewNode = newIdxToNodeMap.get(ranNodeIdx);
                newNode.random = ranNewNode;
            }
            node = node.next;
        }
        return newIdxToNodeMap.get(0);
    }

    public static void main(String[] args) {
        P138_CopyListWithRandomPointer p = new P138_CopyListWithRandomPointer();

        Node n1 = new Node(7);
        Node n2 = new Node(13);
        Node n3 = new Node(11);
        Node n4 = new Node(10);
        Node n5 = new Node(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n2.random = n1;
        n3.random = n5;
        n4.random = n3;
        n5.random = n1;

        Node newHead = p.copyRandomList(n1);
        while (newHead != null){
            Integer ranVal = newHead.random == null ? null: newHead.random.val;
            System.out.print(newHead.val + "(" + ranVal + ") -> ");     //7(null) -> 13(7) -> 11(1) -> 10(11) -> 1(7)
            newHead = newHead.next;
        }
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
