package study.multi_thread;


import java.util.concurrent.locks.ReentrantLock;

/**
 * 240715
 * 实验锁【可重入】的概念。代码由gpt生成。
 */
public class LockReentrantTest {

    private ReentrantLock lock = new ReentrantLock();

    public void method1() {
        lock.lock();
        try {
            System.out.println("Method 1: Acquired lock");
            method2();
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        //因为锁是可重入的，所以下面的代码能继续执行
        lock.lock();
        try {
            System.out.println("Method 2: Acquired lock");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockReentrantTest example = new LockReentrantTest();
        example.method1();
    }
}