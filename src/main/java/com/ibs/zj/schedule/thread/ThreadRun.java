package com.ibs.zj.schedule.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import com.ibs.zj.model.ThreadRunMsgInfo;

public class ThreadRun {

	
	// 定义线程池--假定有16个线程
	private final ExecutorService threadPool = Executors.newFixedThreadPool(16);
	
	// 最多允许10个线程同事跑
	private static final Integer MAXTHREAD = 10;
	
	// 定义容器队列
	private BlockingQueue<FutureTask<ThreadRunMsgInfo>> queue=new ArrayBlockingQueue<FutureTask<ThreadRunMsgInfo>>(500);
	
	
	/**
	 * 初始化线程池
	 * 
	 */
	public void initThreadPool(){
		for(int j=0;j<MAXTHREAD;j++){
			threadPool.submit(new Runnable() {
				@Override
				public void run() {
					while(true){
						try {
							queue.take().run();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
	}
	
	/**
	 * 加入任务线程到队列
	 * 
	 * @param task
	 */
	public void putTask(FutureTask<ThreadRunMsgInfo> task){
		try {
			queue.put(task);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 取出队列里面的线程
	 * 
	 * @return
	 */
	public FutureTask<ThreadRunMsgInfo> takeTask(){
		try {
			return queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public BlockingQueue<FutureTask<ThreadRunMsgInfo>> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<FutureTask<ThreadRunMsgInfo>> queue) {
		this.queue = queue;
	}

	public ExecutorService getThreadPool() {
		return threadPool;
	}
	
}
