package com.zzjy.abgame.util;

import com.zzjy.abgame.bean.BirdBean;
import com.zzjy.abgame.bird.BlackBird;
import com.zzjy.abgame.bird.BlueBird;
import com.zzjy.abgame.bird.RedBird;

/**
 * 鸟工厂类
 * @author Administrator
 *
 */
public final class BirdFactory {
	/**
	 * 获得鸟子类的方法
	 * @param id
	 * @return
	 */
	public static BirdBean getBird(int id) {
		//根据鸟的ID选择
		switch (id) {
		case 1:
			//选1时创建红鸟对象
			return new RedBird();
		case 2:
			//选1时创建红鸟对象
			return new BlueBird();
		case 3:
			//选1时创建红鸟对象
			return new BlackBird();
		default:
			//选错时返回null
			return null;
		}
	}
}
