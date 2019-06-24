package com.zzjy.abgame.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zzjy.abgame.bean.PlayerBean;
import com.zzjy.abgame.exception.AppException;
import com.zzjy.abgame.exception.SysException;
import com.zzjy.abgame.util.DBUtil;
import com.zzjy.abgame.util.Input;
import com.zzjy.abgame.util.RegChk;

public class PlayersManager {
	private static PlayersManager pm = new PlayersManager();
	//创建DBUtil对象
	DBUtil db = new DBUtil();
	
	private PlayersManager() {
		
	}
	public static PlayersManager getInstance() {
		return pm;
	}
	
	/**
	 * 新增玩家时的新信息存储类
	 * @return
	 */
	public PlayerBean addP() {
		//创建新玩家bean
		PlayerBean player = new PlayerBean();
		//写入新数据进bean中
		player.setLoginname(Input.getString("用户名"));
		player.setPassword(Input.getString("密码"));
		player.setNickname(Input.getString("昵称"));
		player.setSex(Input.getString("性别"));
		player.setAge(Input.getInt("年龄"));
		//返回bean备用
		return player;
	}
	
	/**
	 * 修改玩家前储存数据的类
	 * @return
	 */
	public PlayerBean updateP() {
		//新建玩家bean
		PlayerBean player = new PlayerBean();
		//输入信息并封装进bean
		player.setPassword(Input.getNullString("密码"));
		player.setNickname(Input.getNullString("昵称"));
		player.setSex(Input.getNullString("性别"));
		player.setAge(Input.getInt("年龄"));
		//返回这个bean
		return player;
	}
	
	/**
	 * 添加玩家的方法
	 * @return
	 * @throws SysException
	 * @throws AppException 
	 */
	public int addPlayer(PlayerBean play) throws SysException, AppException {
		for (PlayerBean player : pm.queryAll()) {
			if(player.getLoginname().equals(play.getLoginname())) {
				throw new AppException(105, "账号已存在，请重新输入！");
			}
		}
		//判断输入是否符合规则
		if(!play.getLoginname().matches(RegChk.CHKLOGINNAME)) {
			throw new AppException(1002, "账号必须要有字母");
		}
		//判断输入是否符合规则
		if(!play.getPassword().matches(RegChk.CHKPASSWORD)) {
			throw new AppException(1003, "密码必须要有字母和数字，长度不小于6位");
		}
		//判断输入是否符合规则
		if(!play.getNickname().matches(RegChk.CHKNICKNAME)) {
			throw new AppException(1004, "昵称必须是汉字");
		}
		//判断输入是否符合规则
		if(!play.getSex().matches(RegChk.CHKGENDER)) {
			throw new AppException(1005, "性别必须是男或女");
		}
		//判断输入是否符合规则
		if(!(play.getAge()+"").matches(RegChk.CHKAGE)) {
			throw new AppException(1006, "年龄必须是正整数，不大于99");
		}
		
		//写sql语句
		String sql = "insert into player(loginname,password,nickname,sex,age) values(?,?,?,?,?);";
		//创建Object类型数组
		Object [] obj = {play.getLoginname(),play.getPassword(),play.getNickname(),play.getSex(),play.getAge()};
		//调用增删改方法将sql和数组作为参数
		return db.exeUpdate(sql, obj);
	}
	
	/**
	 * 删除玩家的方法
	 * @param id
	 * @return
	 * @throws SysException
	 */
	public int deletePlayer(int id) throws SysException {
		//写sql语句
		String sql = "delete from player where id=?;";
		//创建Object类型数组
		Object [] obj = {id};
		//提示是否确定删除数据
		System.out.println("您确定要删除该玩家吗？输入1删除，输入0放弃。");
		switch (Input.getInt("您的选择")) {
			case 1:
				//输入1直接跳过
				break;
			case 0:
				//输入0直接返回0
				return 0;
			default:
				System.out.println("请输入正确的选择！");
				break;
		}
		//调用增删改方法将sql和数组作为参数
		return db.exeUpdate(sql, obj);
	}
	
	/**
	 * 修改玩家
	 * @param id
	 * @return
	 * @throws SysException
	 * @throws AppException 
	 */
	public int updatePlayer(int id) throws SysException, AppException {
		//将根据该id查询到的玩家存进player
		PlayerBean player = pm.queryById(id);
		//当player不为空，即查到了玩家数据时
		if(player!=null) {
			//将新增玩家信息存进player1
			PlayerBean player1 = updateP();
			
			//当要修改的玩家密码为空或空串时，将查到的原数据赋值回去
			if("".equals(player1.getPassword())||player1.getPassword()==null) {
				player1.setPassword(player.getPassword());
			}
			//当要修改的玩家用户名为空或空串时，将查到的原数据赋值回去
			if("".equals(player1.getNickname())||player1.getNickname()==null) {
				player1.setNickname(player.getNickname());
			}
			//当要修改的玩家用户名为空或空串时，将查到的原数据赋值回去
			if("".equals(player1.getSex())||player1.getSex()==null) {
				player1.setSex(player.getSex());
			}
			
			//判断输入是否符合规则
			if(!player1.getPassword().matches(RegChk.CHKPASSWORD)) {
				throw new AppException(1003, "密码必须要有字母和数字，长度不小于6位");
			}
			//判断输入是否符合规则
			if(!player1.getNickname().matches(RegChk.CHKNICKNAME)) {
				throw new AppException(1004, "昵称必须是汉字");
			}
			//判断输入是否符合规则
			if(!player1.getSex().matches(RegChk.CHKGENDER)) {
				throw new AppException(1005, "性别必须是男或女");
			}
			//判断输入是否符合规则
			if(!(player1.getAge()+"").matches(RegChk.CHKAGE)) {
				throw new AppException(1006, "年龄必须是正整数，不大于99");
			}
			
			//写sql语句
			String sql = "update player set password=?,nickname=?,sex=?,age=? where id=?";
			//创建Object类型数组
			Object [] obj = {player1.getPassword(),player1.getNickname(),player1.getSex(),player1.getAge(),id};
			//调用增删改方法将sql和数组作为参数
			return db.exeUpdate(sql, obj);
		}else {
			System.out.println("没有该玩家");
		}
		return 0;
		
	}
	
	/**
	 * 查询所有玩家
	 * @return
	 * @throws SysException 
	 */
	public List<PlayerBean> queryAll() throws SysException {
		//写sql语句
		String sql = "select * from player;";
		//创建Object类型数组，因为方法参数原因，哪怕没有元素也要有一个
		Object [] obj = {};
		//创建装玩家信息的集合
		List<PlayerBean> players = new ArrayList<>();
		//将查询到的信息赋值给另一个List<Map<>>集合，与方法返回类型相同
		List<Map<String,Object>> list = db.exeQuery(sql, obj);
		//遍历集合并给map集合赋值，准备转换为PlayerBean类型数据
		for (Map<String, Object> map : list) {
			//调用map转换bean的方法将转换来的PlayerBean数据加进list集合
			players.add(mapToBean(map));
		}
		//返回players的玩家信息集合
		return players;
	}
	
	/**
	 * 根据id查询玩家
	 * @param id
	 * @return
	 * @throws SysException
	 */
	public PlayerBean queryById(int id) throws SysException {
		//写sql语句
		String sql = "select * from player where id=?;";
		//创建Object数组
		Object [] obj = {id};
		//将查询到的数据赋值给List<Map<>>集合
		List<Map<String,Object>> list = db.exeQuery(sql, obj);
		//再循环赋值给map集合，并转换为PlayerBean数据返回
		for (Map<String, Object> map : list) {
			return mapToBean(map);
		}
		return null;
		
	}
	/**
	 * 验证玩家登陆
	 * @param loginname
	 * @param password
	 * @return
	 * @throws SysException
	 */
	public PlayerBean chkLogin(String loginname,String password) throws SysException{
		//写sql语句
		String sql = "select * from player where loginname=? and password=?;";
		//创建Object数组，将用户名和密码作为参数
		Object [] obj = {loginname,password};
		//从查询方法中获取到的List<Map<>>集合数据循环赋值给map集合
		for (Map<String, Object> map : db.exeQuery(sql, obj)) {
			//返回查询到并转换为playerbean类型的玩家信息
			return mapToBean(map);
		}
		//未查到返回空
		return null;
		 
	}
	
	/**
	 * map集合通过key将value转换为PlayerBean数据的方法
	 * @param map
	 * @return
	 */
	private PlayerBean mapToBean(Map<String , Object> map) {
		//创建新bean
		PlayerBean players = new PlayerBean();
		//将map集合中的数据根据key将value取出并赋值，封装进bean
		players.setId(Integer.parseInt(String.valueOf(map.get("id"))));
		players.setLoginname(String.valueOf(map.get("loginname")));
		players.setPassword(String.valueOf(map.get("password")));
		players.setNickname(String.valueOf(map.get("nickname")));
		players.setSex(String.valueOf(map.get("sex")));
		players.setAge(Integer.parseInt(String.valueOf(map.get("age"))));
		//返回这个bean
		return players;
		
	}
	
	
	
	
}
