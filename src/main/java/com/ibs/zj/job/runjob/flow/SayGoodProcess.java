package com.ibs.zj.job.runjob.flow;

import com.ibs.zj.service.common.SeqLinkedList;
import com.ibs.zj.service.common.ServiceExecuteInfo;

public class SayGoodProcess implements ServiceExecuteInfo {

	@Override
	public boolean invoke(SeqLinkedList seqList) throws Exception {

		System.out.println("***********Good**********************");
		
		return seqList.nextExec();
	}

	@Override
	public boolean rollBackInvoke(SeqLinkedList seqList) throws Exception {
		
		return seqList.rollExec();
	}

}
