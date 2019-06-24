package com.zzjy.abgame.bean;

public class AdminBean {
	//管理员登录名
	private String loginname;
	//管理员密码
	private String password;
	//最大登陆次数
	private int logintimes;
	
	
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLogintimes() {
		return logintimes;
	}
	@Override
	public String toString() {
		return "AdminBean [loginname=" + loginname + ", password=" + password
				+ ", logintimes=" + logintimes + "]";
	}
	public void setLogintimes(int logintimes) {
		this.logintimes = logintimes;
	}
}
