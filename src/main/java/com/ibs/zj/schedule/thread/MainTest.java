package com.ibs.zj.schedule.thread;

public class MainTest {

	public static void main(String[] args) throws Exception {

		ThreadRun threadRun = new ThreadRun();
		// 初始化线程池--往线程池里面增加10个线程--线程池容量16个
		threadRun.initThreadPool();
		
		// 往队列里面加入线程任务
		ThreadProcess threadProcess = new ThreadProcess(threadRun);
		threadProcess.process();
		
	}
	
}
