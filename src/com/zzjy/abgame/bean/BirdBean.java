package com.zzjy.abgame.bean;
/**
 * 鸟Bean
 * @author Administrator
 *
 */
public class BirdBean {
	//小鸟ID
	protected int id;
	//小鸟颜色
	protected String color;
	//小鸟攻击力
	protected int attack;
	//小鸟命中率
	protected int hit;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	/**
	 * 小鸟展示方法
	 */
	public void display() {
		//使用的是父类的属性，显示子类的赋值
		System.out.println(getColor()+"小鸟准备攻击！它的命中率为："+getHit()+"%，它的攻击力为："+getAttack());
	}
	
	
	/**
	 * 小鸟攻击的方法
	 * @return
	 */
	public int hit() {
		//设定随机数并和小鸟的命中率对比
		if(getHit()>Math.round(Math.random()*100)) {
			//命中语句
			System.out.println("“Boom！”打中了野猪大王！Lucky！（命中目标）");
			//返回攻击值当分数
			return getAttack();
		}else {
			//未命中语句
			System.out.println("“额 额 。。。”没有打中！衰！！（没有命中）");
			//返回未命中分数为0
			return 0;
		}
	}
	
}
