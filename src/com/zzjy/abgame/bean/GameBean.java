package com.zzjy.abgame.bean;
/**
 * 游戏数据类
 * @author Administrator
 *
 */
public class GameBean {
	//游戏编号
	private int id;
	//游戏时间
	private String time;
	//分数
	private int score;
	//玩家游戏次数
	private int count;
	//玩家游戏幸运指数
	private double lucky;
	//游戏玩家信息
	private String loginname;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getLucky() {
		return lucky;
	}
	public void setLucky(double lucky) {
		this.lucky = lucky;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	
	
}
