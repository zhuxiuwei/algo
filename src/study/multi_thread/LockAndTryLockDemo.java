package study.multi_thread;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 240715
 * Lock & TryLock Demo。代码由gpt生成。
 */
public class LockAndTryLockDemo {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("模拟多个线程并发访问共享资源 -- tryLock版");
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Worker(i));
            thread.start();
        }

        Thread.sleep(8 * 1000);

        System.out.println("模拟多个线程并发访问共享资源 -- lock版");
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Worker2(i));
            thread.start();
        }
    }

    //tryLock版
    static class Worker implements Runnable {
        private int id;

        public Worker(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            // 尝试获取锁
            boolean acquired = false;
            try {
                //如果不加尝试等待时长，获取锁失败的线程，会立即打印获取锁失败
                acquired = lock.tryLock(10, TimeUnit.MILLISECONDS);  //感觉在tryLock获取到锁 或者 超时了 之前，下面的代码不会执行
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程 " + id +"----");
            if (acquired) {
                try {
                    // 执行业务逻辑
                    System.out.println("线程 " + id + " 获取到了锁，开始执行业务逻辑");
                    Thread.sleep(1000);
                    System.out.println("线程 " + id + " 完成了业务逻辑");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    lock.unlock();
                    System.out.println("线程 " + id + " 释放了锁");
                }
            } else {
                // 未获取到锁，可以选择等待或者放弃
                System.out.println("线程 " + id + " 未获取到锁，放弃执行");
            }
        }
        /*
        当超时等待时间没有设置，或者设置很短时间的输出：
        线程 2----
        线程 2 获取到了锁，开始执行业务逻辑
        线程 3----
        线程 0----
        线程 0 未获取到锁，放弃执行
        线程 1----
        线程 1 未获取到锁，放弃执行
        线程 4----
        线程 3 未获取到锁，放弃执行
        线程 4 未获取到锁，放弃执行
        线程 2 完成了业务逻辑
        线程 2 释放了锁
         */
    }


    //lock版
    static class Worker2 implements Runnable {
        private int id;

        public Worker2(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            //和tryLock获取失败立即返回不同，lock方法会一直等待直到获取到锁。
            lock.lock();
            try {
                // 执行业务逻辑
                System.out.println("线程 " + id + " 获取到了锁，开始执行业务逻辑");
                Thread.sleep(1000);
                System.out.println("线程 " + id + " 完成了业务逻辑");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();  //如果注释掉，第一个获取锁的线程执行完，就整个不动卡死了。
                System.out.println("线程 " + id + " 释放了锁");
            }
        }

        /*
        线程 0 获取到了锁，开始执行业务逻辑
        线程 0 完成了业务逻辑
        线程 0 释放了锁
        线程 1 获取到了锁，开始执行业务逻辑
        线程 1 完成了业务逻辑
        线程 1 释放了锁
        线程 2 获取到了锁，开始执行业务逻辑
        线程 2 完成了业务逻辑
        线程 2 释放了锁
        线程 4 获取到了锁，开始执行业务逻辑
        线程 4 完成了业务逻辑
        线程 4 释放了锁
        线程 3 获取到了锁，开始执行业务逻辑
        线程 3 完成了业务逻辑
        线程 3 释放了锁
         */
    }
}