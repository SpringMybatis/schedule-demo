package com.ibs.zj.job.runjob;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ibs.zj.service.common.SeqLinkedList;
import com.ibs.zj.service.common.ServiceExecuteInfo;

public class ExecJobInit implements Job {

	/**
	 * 任务执行流程
	 */
	private ServiceExecuteInfo[] jobExecuteFlow;

	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		// 取得所传递的参数
		Map<String, Object> paramMap = (Map<String, Object>) dataMap.get("jobParams");
		SeqLinkedList seqParam = new SeqLinkedList();
		// 放入job参数信息
		if (null != paramMap) {
			Iterator<Entry<String, Object>> iter = paramMap.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, Object> entry = iter.next();
				seqParam.putParam(entry.getKey(), entry.getValue());
			}
		}
		// 将所有代码流程添加到容器中
		seqParam.addExec(jobExecuteFlow);
		boolean exec = false;
		try {
			exec = seqParam.nextExec();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放内存
		seqParam = null;
		System.out.println(exec);
	}

	public ServiceExecuteInfo[] getJobExecuteFlow() {
		return jobExecuteFlow;
	}

	public void setJobExecuteFlow(ServiceExecuteInfo[] jobExecuteFlow) {
		this.jobExecuteFlow = jobExecuteFlow;
	}

}
