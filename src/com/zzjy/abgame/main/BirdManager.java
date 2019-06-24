package com.zzjy.abgame.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zzjy.abgame.bean.BirdBean;
import com.zzjy.abgame.exception.SysException;
import com.zzjy.abgame.ui.Menu;
import com.zzjy.abgame.util.DBUtil;
import com.zzjy.abgame.util.Input;

/**
 * 鸟管理类
 * @author Administrator
 *
 */
public class BirdManager {
	private static BirdManager bm = new BirdManager();
	//创建DBUtil对象
	DBUtil db = new DBUtil();
	
	//创建鸟数据初始化的集合
	private List<BirdBean> birds = new ArrayList<BirdBean>();
	
	public List<BirdBean> getBirds() {
		return birds;
	}
	public void setBirds(List<BirdBean> birds) {
		this.birds = birds;
	}
	
	private BirdManager() {
		for(int i = 1;i<=3;i++) {
			//创建鸟bean，准备赋值
			BirdBean bird = new BirdBean();
			//将鸟id从数据库取出并赋值给鸟信息bean
			bird.setId(getBird(i).getId());
			//将鸟颜色从数据库取出并赋值给鸟信息bean
			bird.setColor(getBird(i).getColor());
			//将鸟攻击力从数据库取出并赋值给鸟信息bean
			bird.setAttack(getBird(i).getAttack());
			//将鸟命中率从数据库取出并赋值给鸟信息bean
			bird.setHit(getBird(i).getHit());
			//将塞好数据的鸟装进birds集合
			birds.add(i-1, bird);
		}
	}
	public static BirdManager getInstance() {
		return bm;
	}
	
	
	/**
	 * 修改小鸟颜色的方法
	 * @param color
	 * @param id
	 * @throws SysException
	 */
	public void updateColor(int id,String color) throws SysException {
		//创建Object数组作为调用修改方法的参数
		Object[] obj = {color,id};
		//通过修改行数确认修改
		if(db.exeUpdate("update bird set color=? where id=?;", obj)>0) {
			System.out.println("修改成功");
		}else {
			throw new SysException(101, "数据错误！请检查数据库！");
		}
	}
	
	/**
	 * 修改小鸟的攻击力
	 * @param attack
	 * @param id
	 * @throws SysException
	 */
	public void updateAttack(int id,int attack) throws SysException {
		//创建Object数组作为调用修改方法的参数
		Object[] obj = {attack,id};
		//通过修改行数确认修改
		if(db.exeUpdate("update bird set attack=? where id=?;", obj)>0) {
			System.out.println("修改成功");
		}else {
			throw new SysException(101, "数据错误！请检查数据库！");
		}
	}
	
	/**
	 * 修改小鸟的命中率
	 * @param hit
	 * @param id
	 * @throws SysException
	 */
	public void updateHit(int id,int hit) throws SysException {
		//创建Object数组作为调用修改方法的参数
		Object[] obj = {hit,id};
		//通过修改行数确认修改
		if(db.exeUpdate("update bird set hit=? where id=?;", obj)>0) {
			System.out.println("修改成功");
		}else {
			throw new SysException(101, "数据错误！请检查数据库！");
		}
	}
	
	/**
	 * 修改小鸟的方法
	 */
	public void changeBird(int id) {
		//小鸟1修改开关
		boolean birdUpdate1 = true;
		//小鸟1修改循环
		while(birdUpdate1) {
			//小鸟修改菜单
			Menu.displayBirdUpdate();
			switch (Input.getInt("您的选项")) {
			case 1:
				try {
					//调用修改小鸟颜色的方法
					updateColor(id, Input.getString("小鸟的颜色"));
				} catch (SysException e) {
					// TODO Auto-generated catch block
					//有问题抛异常
					System.out.println(e.getErrMsg());
				}
				break;
			case 2:
				try {
					//调用修改小鸟攻击力的方法
					updateAttack(id, Input.getInt("小鸟的攻击力"));
				} catch (SysException e) {
					// TODO Auto-generated catch block
					//有问题抛异常
					System.out.println(e.getErrMsg());
				}
				break;
			case 3:
				try {
					//调用修改小鸟命中率的方法
					updateHit(id, Input.getInt("小鸟的命中率"));
				} catch (SysException e) {
					// TODO Auto-generated catch block
					//有问题抛异常
					System.out.println(e.getErrMsg());
				}
				break;
			case 0:
				//关闭小鸟1修改开关
				birdUpdate1 = false;
				break;
			default:
				System.out.println("请输入正确的选项");
				break;
			}
			break;
		}
	}
	
	/**
	 * 获取单个鸟的方法，随时备用
	 * @param id
	 * @return
	 * @throws SysException
	 */
	public BirdBean getBird(int id){
		//sql语句
		String sql = "select * from bird where id=?";
		//创建Object数组
		Object [] obj = {id};
		//创建鸟bean对象准备存数据
		BirdBean bird = new BirdBean();
		try {
			//循环将查到的list集合赋值给map集合
			for (Map<String, Object> map : db.exeQuery(sql, obj)) {
				//调用map转换为bean类型的方法，将map的value赋值给birdBean
				bird = mapToBean(map);
			}
			//返回取到的那只鸟信息
			return bird;
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * map集合通过key将value转换为PlayerBean数据的方法
	 * @param map
	 * @return
	 */
	private BirdBean mapToBean(Map<String , Object> map) {
		//创建鸟bean
		BirdBean bird = new BirdBean();
		//获取id并赋值
		bird.setId(Integer.parseInt(String.valueOf(map.get("id"))));
		//获取颜色
		bird.setColor(String.valueOf(map.get("color")));
		//获取攻击力
		bird.setAttack(Integer.parseInt(String.valueOf(map.get("attack"))));
		//获取命中率
		bird.setHit(Integer.parseInt(String.valueOf(map.get("hit"))));
		//返回塞好的bean数据
		return bird;
		
	}
	
	
/*	public static void main(String[] args) {
		BirdManager bm = BirdManager.getInstance();
		
	}
*/
	
}
