package com.ibs.zj.schedule.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

import com.ibs.zj.model.ThreadRunMsgInfo;

public class ThreadProcess {

	private ThreadRun jobContainer;
	
	public ThreadProcess(ThreadRun threadRun){
		this.jobContainer = threadRun;
	}
	
	public void process() throws Exception{
		// 运行结果统计
		final List<ThreadRunMsgInfo> reList = new ArrayList<ThreadRunMsgInfo>();
		
		// 信号量4
		final Semaphore sem = new Semaphore(5);
		
		// 执行计数器
		final CountDownLatch end = new CountDownLatch(50);  
		
		// 假设50个处理对象
		List<ThreadRunMsgInfo> list = new ArrayList<ThreadRunMsgInfo>();
		for(int i=0;i<50;i++){
			ThreadRunMsgInfo thread = new ThreadRunMsgInfo();
			thread.setThredId(i);
			list.add(thread);
		}
		
		// 创建50个线程加入线程队列
		for (final ThreadRunMsgInfo threadRunMsgInfo : list) {
			/*FutureTask<ThreadRunMsgInfo> task = new FutureTask<ThreadRunMsgInfo>(
					new Runnable() {
						@SuppressWarnings("static-access")
						@Override
						public void run() {
							try {
								sem.acquire();
								Thread.currentThread().sleep(2*1000);
								System.out.println("--- "+threadRunMsgInfo.getThredId()+" ---");
							} catch (Exception e) {
								e.printStackTrace();
							}finally{
								sem.release();
								end.countDown();
							}
						}
					}, null);*/
			FutureTask<ThreadRunMsgInfo> task = new FutureTask<ThreadRunMsgInfo>(new Callable<ThreadRunMsgInfo>() {
				@SuppressWarnings("static-access")
				@Override
				public ThreadRunMsgInfo call() throws Exception {
					ThreadRunMsgInfo t = null;
					try {
						sem.acquire();
						t = new ThreadRunMsgInfo();
						Thread.currentThread().sleep(2*1000);
						System.out.println("--- "+threadRunMsgInfo.getThredId()+" ---");
						t.setThredId(threadRunMsgInfo.getThredId());
						t.setThreadResult(threadRunMsgInfo.getThredId()>24);
						// 返回结果
						reList.add(t);
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						sem.release();
						end.countDown();
					}
					return t;
				}
			});
			jobContainer.putTask(task);
			// reList.add(task.get());
		}
		end.await();
		System.out.println("所有任务执行完毕......");
		jobContainer.getThreadPool().shutdown();
		System.out.println("线程池关闭,禁止添加任务......");
		for(ThreadRunMsgInfo ta:reList){
			System.out.println(ta.getThredId()+":"+ta.isThreadResult());
		}
	}

	public ThreadRun getJobContainer() {
		return jobContainer;
	}

	public void setJobContainer(ThreadRun jobContainer) {
		this.jobContainer = jobContainer;
	}
	
}
