package com.ibs.zj.service;

import com.ibs.zj.model.TaskJobMsg;


public interface QuartzManagerService {

	/**
	 * 添加任务信息
	 * 
	 * @param job
	 * @throws Exception
	 */
	public abstract void addJob(TaskJobMsg job) throws Exception;

}