package com.zzjy.abgame.bean;

/**
 * 玩家信息类
 * @author Administrator
 *
 */
public class PlayerBean {
	//玩家ID
	private int id;
	//玩家账号
	private String loginname;
	//玩家密码
	private String password;
	//玩家昵称
	private String nickname;
	//玩家性别
	private String sex;
	//玩家年龄
	private int age;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
