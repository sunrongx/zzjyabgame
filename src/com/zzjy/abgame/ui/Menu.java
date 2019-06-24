package com.zzjy.abgame.ui;

import com.zzjy.abgame.bean.AdminBean;
import com.zzjy.abgame.exception.SysException;
import com.zzjy.abgame.main.BirdManager;
import com.zzjy.abgame.util.AdminXML;

public class Menu {
		
	static AdminXML ax = AdminXML.getInstance();
	static BirdManager bm = BirdManager.getInstance();
	
	/**
	 * 游戏开始时显示的首页
	 */
	public static void displayMain() {
		System.out.println("***************************************\n*************************************");
		System.out.println("愤 怒 的 小 鸟\n");
		System.out.println("请选择登录方式：1、玩家登陆\n2、管理员登录\t0、退出\n");
		System.out.println("***************************************\n*************************************");
	}
	
	/**
	 *  选择进入玩家菜单时
	 */
	public static void displayPlayer() {
		System.out.println("***************************************\n*************************************");
		System.out.println("愤 怒 的 小 鸟\n");
		System.out.println("1、开始游戏\n2、查看成绩\n0、返回上级");
		System.out.println("***************************************\n*************************************");
	}

	/**
	 *  选择进入管理员菜单时
	 */
	public static void displayAdmin() {
		System.out.println("***************************************\n*************************************");
		System.out.println("系统管理\n\n1、新增玩家\n2、修改玩家\n3、删除玩家\n"
				+ "4、查询玩家\n5、查询游戏\n6、分数统计\n7、参数设置\n0、返回");
		System.out.println("***************************************\n*************************************");
	}

/*	public static void hit() {
		System.out.println("“Boom！”打中了野猪大王！Lucky！（命中目标）");
	}

	public static void miss() {
		System.out.println("“额 额 。。。”没有打中！衰！！（没有命中）");
	}
*/
	
	/**
	 *  进入游戏后的小鸟选择提示信息
	 * @throws SysException 
	 */
	public static void birdChoice() throws SysException {
		System.out.println("***************************************");
		System.out.println("\t请选择小鸟");
		//从数据库中取出鸟的方法并遍历
		for(int i = 1;i<=bm.getBirds().size();i++) {
			System.out.println(i+"、"+bm.getBird(i).getColor()+"的小鸟 攻击力："+bm.getBird(i).getAttack()+" 命中率："+bm.getBird(i).getHit()+"%");
		}		
		System.out.println("\n0、返 回 上 级");
		System.out.println("请选择（需要选择三个小鸟发起攻击，可相同也可不同）：");
		System.out.println("***************************************");
	}

	/**
	 *  输入错误时的提示语句
	 */
/*	public static void birdReturn() {
		System.out.println("请输入正确的数字！");
	}
*/
	
	/**
	 *  选择进入游戏后的游戏本体
	 */
	public static void gameBegin() {
		System.out.println("游戏仍未完成，游戏本体请在有生之年继续期待！");
	}

	public static void displayParameter() {
		System.out.println("***************************************");
		
		System.out.println("请选择要修改的参数");
		System.out.println("编号1、"+bm.getBird(1).getColor()+"的小鸟 攻击力："+bm.getBird(1).getAttack()+" 命中率："+bm.getBird(1).getHit()+"%");
		System.out.println("编号2、"+bm.getBird(2).getColor()+"的小鸟 攻击力："+bm.getBird(2).getAttack()+" 命中率："+bm.getBird(2).getHit()+"%");
		System.out.println("编号3、"+bm.getBird(3).getColor()+"的小鸟 攻击力："+bm.getBird(3).getAttack()+" 命中率："+bm.getBird(3).getHit()+"%");
		System.out.println("4、管理员登录名");
		System.out.println("5、管理员密码");
		System.out.println("6、管理员最大登录次数");
		System.out.println("0、返 回 上 级");
	}
	
	public static void displayBirdUpdate() {
		System.out.println("1、修改小鸟的颜色");
		System.out.println("2、修改小鸟的攻击力");
		System.out.println("3、修改小鸟的命中率");
		System.out.println("0、返回上级");
	}
	
/*	public static void displayAdmins() {
		System.out.println("\t请选择你要修改的参数：");
		System.out.println("1、管理员登录名为："+ax.getAdminname());
		System.out.println("2、管理员密码为："+ax.getAdminPassword());
		System.out.println("3、管理员登陆次数为："+ax.getLogintimes());
	}
*/
	
	
}
