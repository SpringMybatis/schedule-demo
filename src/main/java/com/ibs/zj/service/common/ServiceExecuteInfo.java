package com.ibs.zj.service.common;
public interface ServiceExecuteInfo {

	/**
	 * 进行正常流程执行的代码
	 * 
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	public boolean invoke(SeqLinkedList seqList) throws Exception;

	/**
	 * 进行回退流程操作
	 * 
	 * @param seqlist
	 * @return
	 * @throws Exception
	 */
	public boolean rollBackInvoke(SeqLinkedList seqList) throws Exception;

}