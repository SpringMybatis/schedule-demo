package com.ibs.zj.service.impl;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.ibs.zj.model.TaskJobMsg;
import com.ibs.zj.service.QuartzManagerService;

/**
 * 任务操作核心控制类
 * 
 * @author liujun
 * 
 * @date 2014年4月11日
 * @vsersion 0.0.1
 */
@Service
public class QuartzManagerServiceImpl implements QuartzManagerService {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibs.gbplatform.report.task.service.impl.job.QuartzManagerService#
	 * setSchedulerFactoryBean
	 * (org.springframework.scheduling.quartz.SchedulerFactoryBean)
	 */
	public void setSchedulerFactoryBean(
			SchedulerFactoryBean schedulerFactoryBean) {
		this.schedulerFactoryBean = schedulerFactoryBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibs.gbplatform.report.task.service.impl.job.QuartzManagerService#
	 * addJob(com.ibs.gbplatform.report.task.model.job.TaskJobMsg)
	 */
	public void addJob(TaskJobMsg job) throws Exception {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobDetail jobDetail = JobBuilder.newJob(job.getJobClasses()).withIdentity(this.getJobName(job), job.getJobGroup()).build();
		// 封装运行时所需要参数
		jobDetail.getJobDataMap().put("jobParams",job.getParam());
		// 运行时的实例对象
		jobDetail.getJobDataMap().put("jobInstance",job.getExecJobInstance());
		// 触发器
		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getJobCron());

		// 按新的cronExpression表达式构建一个新的trigger
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(this.getJobName(job), job.getJobGroup())
				.withSchedule(scheduleBuilder).build();
		scheduler.scheduleJob(jobDetail, trigger);
	}


	private String getJobName(TaskJobMsg job) throws Exception {
		return job.getJobName() + job.getJobId();
	}

	

}
