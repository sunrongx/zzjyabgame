package com.zzjy.abgame.exception;

public class SysException extends Exception{
	/**
	 * 串行ID
	 */
	private static final long serialVersionUID = 4897329182531870425L;
	private int errCode;
	private String errMsg;
	
	public SysException(int errCode,String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	
}
