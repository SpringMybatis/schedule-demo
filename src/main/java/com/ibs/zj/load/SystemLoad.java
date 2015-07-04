package com.ibs.zj.load;

import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.ibs.zj.model.TaskJobMsg;
import com.ibs.zj.service.QuartzManagerService;

public class SystemLoad {

	private String jobCron;
	
	@Autowired
	private Job jobRunService;
	
	@Autowired
	private QuartzManagerService jobManager;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void executeSysLoadJob() throws Exception {
		// 初始化一个job
		TaskJobMsg taskJobMsg = new TaskJobMsg();
		taskJobMsg.setJobCron(jobCron);
		taskJobMsg.setJobName("测试例子");
		taskJobMsg.setJobState(1);
		taskJobMsg.setJobId(1);
		taskJobMsg.setJobGroup("mytestgroup");
		taskJobMsg.setJobClass("com.ibs.zj.job.runjob.ExecJob");
		// 加入schedule管理
		Class classJob = Class.forName(taskJobMsg.getJobClass());
		taskJobMsg.setJobClasses(classJob);
		taskJobMsg.setExecJobInstance(jobRunService);
		// 进行参数封装
		taskJobMsg.putParam("jobInfo", "测试例子");
		jobManager.addJob(taskJobMsg);
		System.out.println("success......");
	}

	public String getJobCron() {
		return jobCron;
	}
	
	@Value("#{propertiesReader['schedule.is.jobCron']}")
	public void setJobCron(String jobCron) {
		this.jobCron = jobCron;
	}

}
