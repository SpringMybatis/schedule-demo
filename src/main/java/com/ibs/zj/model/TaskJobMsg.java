package com.ibs.zj.model;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.quartz.Job;

public class TaskJobMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Jobid执行任务的id
	 */
	private int jobId;

	/**
	 * 执行任务的名字
	 */
	private String jobName;

	/**
	 * 数据配制信息ID
	 */
	private int tpId;

	/**
	 * Job状态:0禁用 1启用 2删除
	 */
	private int jobState;

	/**
	 * 描述信息
	 */
	private String jobDesc;

	/**
	 * 任务运行时间表达式
	 */
	private String jobCron;

	/**
	 * 任务的分组信息
	 */
	private String jobGroup;

	/**
	 * job执行任务的类信息
	 */
	private String jobClass = "com.ibs.gbplatform.report.task.service.impl.job.runJob.ExecJob";

	/**
	 * 当前任务的状态,0，初始化,1，正在执行，2，执行完毕
	 */
	private int jobCurrState;

	/**
	 * 进行任务执行的实例类的名字
	 */
	private Object execJobInstance;

	/**
	 * 执行job的类的信息
	 */
	private Class<? extends Job> jobClasses;

	/**
	 * 参数信息
	 */
	private Map<String, Object> param = new ConcurrentHashMap<String, Object>();

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getTpId() {
		return tpId;
	}

	public void setTpId(int tpId) {
		this.tpId = tpId;
	}

	public int getJobState() {
		return jobState;
	}

	public void setJobState(int jobState) {
		this.jobState = jobState;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getJobCron() {
		return jobCron;
	}

	public void setJobCron(String jobCron) {
		this.jobCron = jobCron;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public int getJobCurrState() {
		return jobCurrState;
	}

	public void setJobCurrState(int jobCurrState) {
		this.jobCurrState = jobCurrState;
	}

	public Object getExecJobInstance() {
		return execJobInstance;
	}

	public void setExecJobInstance(Object execJobInstance) {
		this.execJobInstance = execJobInstance;
	}

	public Class<? extends Job> getJobClasses() {
		return jobClasses;
	}

	public void setJobClasses(Class<? extends Job> jobClasses) {
		this.jobClasses = jobClasses;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public void putParam(String key, Object value) {
		this.param.put(key, value);
	}

	@Override
	public String toString() {
		return "TaskJobMsg [jobId=" + jobId + ", jobName=" + jobName
				+ ", tpId=" + tpId + ", jobState=" + jobState + ", jobDesc="
				+ jobDesc + ", jobCron=" + jobCron + ", jobGroup=" + jobGroup
				+ ", jobClass=" + jobClass + ", jobCurrState=" + jobCurrState
				+ ", execJobInstance=" + execJobInstance + ", jobClasses="
				+ jobClasses + ", param=" + param + "]";
	}

}
