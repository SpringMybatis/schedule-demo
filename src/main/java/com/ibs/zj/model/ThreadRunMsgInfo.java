package com.ibs.zj.model;

public class ThreadRunMsgInfo {

	private int thredId;

	private String thradName;

	private String threadMsg;
	
	private boolean threadResult;

	public int getThredId() {
		return thredId;
	}

	public void setThredId(int thredId) {
		this.thredId = thredId;
	}

	public String getThradName() {
		return thradName;
	}

	public void setThradName(String thradName) {
		this.thradName = thradName;
	}

	public String getThreadMsg() {
		return threadMsg;
	}

	public void setThreadMsg(String threadMsg) {
		this.threadMsg = threadMsg;
	}

	public boolean isThreadResult() {
		return threadResult;
	}

	public void setThreadResult(boolean threadResult) {
		this.threadResult = threadResult;
	}

}
