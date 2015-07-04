package com.ibs.zj.job.runjob;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ExecJob implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		// 取得定时执行的id
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();

		Job jobInstance = (Job) dataMap.get("jobInstance");
		
		jobInstance.execute(context);

	}

}
