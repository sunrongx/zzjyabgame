package com.zzjy.abgame.main;

import com.zzjy.abgame.bean.AdminBean;
import com.zzjy.abgame.ui.Menu;
import com.zzjy.abgame.util.AdminXML;
import com.zzjy.abgame.util.Constants;
import com.zzjy.abgame.util.Input;
/**
 * 管理员管理类
 * @author Administrator
 *
 */
public final class AdminManager {
	private AdminXML ax = AdminXML.getInstance();
	private AdminBean ab = new AdminBean();
	
	//保留私有属性并用自己赋值等待单例化
	private static AdminManager am = new AdminManager();
	/**
	 * 私有构造
	 **/
	private AdminManager() {
		// TODO Auto-generated constructor stub
		
	}
	/**
	 * 让外界可以获取静态实例，实现单例化
	 * @return
	 **/
	public static AdminManager getInstance() {
		return am;
	}
	
	
	
	/**
	 * 修改管理员用户名
	 */
	public void updateAdminName() {
		if(ax.updateAdminname(Input.getString("新的管理员用户名"))) {
			System.out.println("修改成功");
		}else {
			System.out.println("修改失败");
		}
	}
	
	/**
	 * 修改管理员密码
	 */
	public void updateAdminPassword() {
		if(ax.updatePassword(Input.getString("新的管理员密码"))) {
			System.out.println("修改成功");
		}else {
			System.out.println("修改失败");
		}
	}
	
	/**
	 * 修改管理员最大登陆次数
	 */
	public void updateAdminLogintimes() {
		if(ax.updateLogintimes(Input.getString("新的管理员登陆次数"))) {
			System.out.println("修改成功");
		}else {
			System.out.println("修改失败");
		}
	}
	
	
	
	// 验证管理员账号密码
	public boolean chkAdmin() {
		//设置管理员账号密码和最大登陆次数，用于每次修改并调用验证方法时重新赋值以即时让修改生效
		ab.setLoginname(ax.getAdminname());
		ab.setPassword(ax.getAdminPassword());
		ab.setLogintimes(ax.getLogintimes());
		//当静态变量与获取控制台的账号及密码匹配
		if (ab.getLoginname().equals(Input.getString("账号")) & ab.getPassword().equals(Input.getString("密码"))) {
			//提示成功
			System.out.println("管理员登陆成功！");
			//返回真
			return true;
		} else {
			//否则返回假
			return false;
		}

	}

	

}
