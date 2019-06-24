package com.zzjy.abgame.bird;

import com.zzjy.abgame.bean.BirdBean;

/**
 * 黑鸟子类
 * @author Administrator
 *
 */
public class BlackBird extends BirdBean{
	/**
	 * 黑鸟构造给父类属性赋值
	 */
	public BlackBird() {
		id = 3;
		color = "笨重的黑色";
		hit = 30;
		attack = 200;
		
	}
	
}
