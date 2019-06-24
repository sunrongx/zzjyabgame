package com.zzjy.abgame.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zzjy.abgame.bean.BirdBean;
import com.zzjy.abgame.bean.GameBean;
import com.zzjy.abgame.bean.PlayerBean;
import com.zzjy.abgame.exception.SysException;
import com.zzjy.abgame.ui.Menu;
import com.zzjy.abgame.util.DBUtil;
import com.zzjy.abgame.util.DateUtil;
import com.zzjy.abgame.util.Input;

/**
 * 游戏管理类
 * @author Administrator
 *
 */
public class GamesManager {
	private static GamesManager gm = new GamesManager();
	//创建DBUtil对象
	DBUtil db = new DBUtil();
	//创建鸟管理单例
	BirdManager bm = BirdManager.getInstance();
	
	
	
	private GamesManager() {
		
	}
	public static GamesManager getInstance() {
		return gm;
	}
	
	
	
	/**
	 * 玩游戏的方法
	 * @param player
	 * @throws SysException 
	 */
	public void playGame(PlayerBean player) throws SysException {
		//选鸟的存储集合
		List<Integer> chbirds = new ArrayList<>();
		//提示鸟的信息
		try {
			Menu.birdChoice();
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//总分
		int score = 0;
		//根据选鸟的集合长度循环将鸟的编号赋值给选鸟集合
		for(int i = 0;i<5;i++) {
			//根据鸟的编号输入并获取为选项
			switch (Input.getInt("您要选择的鸟的编号")) {
			case 1:
				//红鸟
				chbirds.add(1);
				break;
			case 2:
				//蓝鸟
				chbirds.add(2);
				break;
			case 3:
				//黑鸟
				chbirds.add(3);
				break;

			default:
				System.out.println("请输入正确的编号！");
				//输入错误时选择机会减1重选
				i--;
				break;
			}
			
		}
		
		//再根据选鸟集合的长度使用数组中选好的编号循环计算分数
		for(int i = 0;i<chbirds.size();i++) {
			//红鸟
			if(chbirds.get(i) == 1) {
				//显示方法
				bm.getBirds().get(0).display();
				//攻击方法并算分
				score += bm.getBirds().get(0).hit();
			}
			//蓝鸟
			if(chbirds.get(i) == 2) {
				//显示方法
				bm.getBirds().get(1).display();
				//攻击方法并算分
				score += bm.getBirds().get(1).hit();
			}
			//黑鸟
			if(chbirds.get(i) == 3) {
				//显示方法
				bm.getBirds().get(2).display();
				//攻击方法并算分
				score += bm.getBirds().get(2).hit();
			}
		}
		//输出总分
		System.out.println("您的最终得分为："+score+"分！");
		
		String sql = "insert into tgame(gametime,score,loginname) values(now(),?,?);";
		Object [] obj = {score,player.getLoginname()};
		db.exeUpdate(sql, obj);
		
	}
	
	/**
	 * 查询所有游戏信息的方法
	 * @return 游戏信息集合
	 * @throws SysException 
	 */
	public List<GameBean> queryAll() throws SysException {
		//sql语句
		String sql = "select * from tgame;";
		//参数数组
		Object [] obj = {};
		//游戏信息存储集合
		List<GameBean> games = new ArrayList<>();
		//遍历集合并拆解为map集合，调用转换方法将map集合的value封装成bean
		for (Map<String, Object> map : db.exeQuery(sql, obj)) {
			//将bean装进游戏信息集合
			games.add(mapToBean(map));
		}
		//返回该游戏集合
		return games;
	}
	
	/**
	 * 根据玩家查询游戏信息
	 * @param player 登陆方法获取到的玩家信息
	 * @return 该玩家的所有游戏数据
	 * @throws SysException
	 */
	public List<GameBean> queryByLoginname(PlayerBean player) throws SysException{
		//sql语句
		String sql = "select * from tgame where loginname=?;";
		//参数数组
		Object [] obj = {player.getLoginname()};
		//创建当前玩家游戏信息集合
		List<GameBean> loginGames = new ArrayList<GameBean>();
		//声明新集合以接收查到的所有游戏信息
		List<Map<String , Object>> list = db.exeQuery(sql, obj);
		//遍历游戏信息并拆成map集合
		for (Map<String, Object> map : list) {
			//调用map转bean方法将value封装进bean
			loginGames.add(mapToBean(map));
		}
		//返回新集合
		return loginGames;
	}
	
	/**
	 * 查询登录名出现次数的方法
	 * @param loginname 获取到的登录名
	 * @return 出现次数
	 * @throws SysException
	 */
	public int getCounts(String loginname) throws SysException {
		//sql语句，查询登录名出现次数
		String sql = "select count(loginname) coun from tgame where loginname=?";
		//参数数组
		Object [] obj = {loginname};
		//将查出的数据赋值给集合list
		List<Map<String , Object>> list = db.exeQuery(sql, obj);
		//将list的唯一一条数据，即该昵称的出现次数返回
		return Integer.parseInt(String.valueOf(list.get(0).get("coun")));
	}
	
	/**
	 * 查询总成绩列表的方法
	 * @return
	 * @throws SysException
	 */
	public List<GameBean> queryByScore() throws SysException{
		//sql语句，查询各玩家分数之和并按玩家昵称分组后按分数高低排序
		String sql = "select loginname,sum(score) score from tgame group by loginname order by score desc;";
		//参数数组
		Object [] obj = {};
		//声明新集合
		List<GameBean> scoreGames = new ArrayList<GameBean>();
		//声明集合以接收查询到的数据
		List<Map<String , Object>> list = db.exeQuery(sql, obj);
		//遍历集合拆成map集合
		for (Map<String, Object> map : list) {
			//调用map转换bean的方法将value封装为bean
			scoreGames.add(mapToBean(map));
		}
		//返回新集合
		return scoreGames;
		
	}
	
	
	
	/**
	 * map集合通过key将value转换为PlayerBean数据的方法
	 * @param map
	 * @return
	 * @throws SysException 
	 */
	private GameBean mapToBean(Map<String , Object> map) throws SysException {
		//创建新bean对象
		GameBean game = new GameBean();
		//封装从map中根据key取到的value值
		game.setTime(String.valueOf(map.get("gametime")));
		game.setScore(Integer.parseInt(String.valueOf(map.get("score"))));
		game.setLoginname(String.valueOf(map.get("loginname")));
		//返回这个bean
		return game;
		
	}
	
	public static void main(String[] args) {
		GamesManager gm = GamesManager.getInstance();
		try {
			System.out.println(gm.getCounts("12"));
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
