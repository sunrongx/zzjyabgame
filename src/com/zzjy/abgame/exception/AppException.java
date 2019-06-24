package com.zzjy.abgame.exception;

public class AppException extends Exception {
	/**
	 * 串行ID
	 */
	private static final long serialVersionUID = 1L;
	//异常信息码
	private int errCode;
	//异常信息
	private String errMsg;
	/**
	 * 带参构造，用形参给实例变量赋值
	 * @param errCode
	 * @param errMsg
	 */
	public AppException(int errCode,String errMsg) {
		//形参异常码给实例赋值
		this.errCode = errCode;
		//形参异常信息给实例赋值
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
