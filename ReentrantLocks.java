package temp.basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLocks {

	
	
	private static class ReentrantDemo extends Thread {
		private ReentrantLock lock;
		private long cTime;
		
		public ReentrantDemo(ReentrantLock lock ,String threadName) {
			super(threadName);
			this.lock = lock;
		}
		
		@Override
		public void run() {
			this.cTime =System.currentTimeMillis();
			System.out.println("Thread "+Thread.currentThread().getName()+" is started at "+cTime);
			lock.lock();
			try {	
			    System.out.println("lock is hold by "+Thread.currentThread().getName()+" waiting time "+ (System.currentTimeMillis()-cTime)+ " in millis" );
			    Thread.sleep(2000);
			}catch(Exception exception) {
				System.out.println("Thread "+Thread.currentThread().getName()+" is interuppted");
			
			}finally {
				lock.unlock();
			}
			
			
			
			
		}
	}
	
	
	
	public static void main(String[] args) throws Exception{
		
		ReentrantLock lock = new ReentrantLock(true);
				
		for(int i=0;i < 10;i++) {
			new ReentrantDemo(lock, " thread- "+i).start();
		}
		Thread.sleep(1000);
		
		while(lock.getQueueLength() > 0) {
			System.out.println("No of Threads waiting at the queue is "+lock.getQueueLength());
			Thread.sleep(1000);
		 
		}

		
	}
	
}
