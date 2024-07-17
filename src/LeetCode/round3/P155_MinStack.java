package LeetCode.round3;

import java.util.Stack;

/**
 * 240717
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 *
 * Constraints:
 *
 * -231 <= val <= 231 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 104 calls will be made to push, pop, top, and getMin.
 */
public class P155_MinStack {

    private Stack<Integer> minHelperStack;
    private Stack<Integer> normalStack;


    /**
     * AC: 4ms, 95%. mem: 36%
     */
    public P155_MinStack() {
        minHelperStack = new Stack<>();
        normalStack = new Stack<>();
    }

    public void push(int val) {
        normalStack.push(val);
        if(!minHelperStack.isEmpty()) {     //！！！ 这个不能忘，否则第一次push会异常。
            int min = Math.min(val, minHelperStack.peek());
            minHelperStack.push(min);
        }else {
            minHelperStack.push(val);
        }
    }

    public void pop() {
        normalStack.pop();
        minHelperStack.pop();
    }

    public int top() {
        return normalStack.peek();
    }

    public int getMin() {
        return minHelperStack.peek();
    }

    public static void main(String[] args) {
        P155_MinStack minStack = new P155_MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());    // return 0
        System.out.println(minStack.getMin()); // return -2
    }
}
