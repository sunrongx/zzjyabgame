package com.zzjy.abgame.bird;

import com.zzjy.abgame.bean.BirdBean;

/**
 * 红鸟子类
 * @author Administrator
 *
 */
public class RedBird extends BirdBean{
	/**
	 * 红鸟构造给父类属性赋值
	 */
	public RedBird() {
		id = 1;
		color = "帅气的红色";
		hit = 50;
		attack = 80;
		
	}
	
}
