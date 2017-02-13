package study.interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 170210 阿里 
 * 有两个线程，线程1打印1,3,5,7,9...... 线程2打印2,4,6,8,10....
 * 写代码，利用这两个线程，打印1,2,3,4,5,6,7,......
 * 
 * ！！！！！如何调用wait()要注意。
 */
public class I170210_Ali_MutileThreadPrint12345 {
	
	//print 1,3,5...
	static class T1 implements Runnable{
		private I170210_Ali_MutileThreadPrint12345 lock;
		private AtomicInteger flag;
		public T1(AtomicInteger flag, I170210_Ali_MutileThreadPrint12345 lock){
			this.flag = flag;
			this.lock = lock;
		}
		
		@Override
		public void run() {
			for (int i = 1; i <= 10; i += 2) {
				synchronized(lock){		//！！ wait 和 notify必须在同步代码块里。
					while(flag.get() != 0){
						try {
							lock.wait();	//！！！！！！！！！！注意，是lock.wait()。不能直接写wait()。否则结果不对。这里卡了半天。其他wait notify同理。！！！！！！！！！！
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.print(i + " ");
					flag.set(1);
					lock.notifyAll();
				}
			}
		}
	}

	//print 2,4,6...
	static class T2 implements Runnable{
		private I170210_Ali_MutileThreadPrint12345 lock;
		private AtomicInteger flag;
		public T2(AtomicInteger flag, I170210_Ali_MutileThreadPrint12345 lock){
			this.flag = flag;
			this.lock = lock;
		}
		
		@Override
		public void run() {
			for (int i = 2; i <= 10; i += 2) {
				synchronized(lock){
					while(flag.get() != 1){
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.print(i + " ");
					flag.set(0);
					lock.notifyAll();
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		I170210_Ali_MutileThreadPrint12345 lock = new I170210_Ali_MutileThreadPrint12345();
		AtomicInteger ai = new AtomicInteger(0);
		ExecutorService es = Executors.newFixedThreadPool(2);
		
		//!!! T1和T2先后顺序不影响结果。
		es.submit(new T2(ai, lock));
		es.submit(new T1(ai, lock));
		es.shutdown();
	}
}

