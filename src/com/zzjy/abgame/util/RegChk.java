package com.zzjy.abgame.util;

/**
 * 正则验证
 * @author Administrator
 *
 */
public final class RegChk {
	
	//必须要有字母
	public static final String CHKLOGINNAME="^(?![0-9]+$)[0-9A-Za-z]{0,16}$";
	//必须要有字母和数字，长度不小于6位
	public static final String CHKPASSWORD="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
	//必须是男或女
	public static final String CHKGENDER="^['男','女']$";
	//必须是正整数，不大于99
	public static final String CHKAGE="^[1-9][0-9]?$";
	//必须是汉字
	public static final String CHKNICKNAME="[\u4e00-\u9fa5]{1,}";	
	

	
/*	//必须要有字母
	public static final String CHKLOGINNAME="^[0-9]*$";
	//必须要有字母和数字，长度不小于6位
	public static final String CHKPASSWORD="^[0-9]*$";
	//必须是男或女
	public static final String CHKGENDER="^[0-9]*$";
	//必须是正整数，不大于99
	public static final String CHKAGE="^[0-9]*$";
	//必须是汉字
	public static final String CHKNICKNAME="^[0-9]*$";	
*/	
	
}
