package com.zzjy.abgame.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zzjy.abgame.exception.SysException;

public class DBUtil {
	private String drive = "com.mysql.jdbc.Driver";
	private String user = "root";
	private String password = "870919";
	private String url = "jdbc:mysql://127.0.0.1:3306/abgame?useEncoding=true&characterEncoding=utf-8";
	
	@SuppressWarnings("unused")
	private Connection GetConn() throws SysException {
		try {
			Class.forName(drive);
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new SysException(2002, "系统错误，请检查系统参数设置！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SysException(2001, "数据库异常，请检查数据库设置！");
		}
		
	}
	
	/**
	 * 增删改的方法
	 * 调用时：1、拼SQL，将字段名设为?
	 * 		2、传参：将要增删改的数据加入到Object类型的数组作为参数
	 * 		3、调用方法：将SQL和该数组作为参数调用执行
	 * @param sql：要执行的预编译语句
	 * @param obj：预编译语句的参数
	 * @return 执行的（光标下移的）行数
	 * @throws SysException 
	 */
	public int exeUpdate(String sql,Object [] obj) throws SysException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			//获取连接
			conn = GetConn();
			//获取预编译对象
			ps = conn.prepareStatement(sql);
			//给SQL注入参数 ：ps.setObject(parameterIndex, x);  
			//parameterIndex：往哪塞数据，x：塞什么数据（下标取得数）
			//当不为空的时候添加数据
			if(obj!=null) {
				//预编译语句对象，从第1个位置即第一个问号开始
				for(int i = 0;i<obj.length;i++) {
					//i+1：SQL语句中第一个位置的问号
					//obj[i]：传入的第1个参数
					ps.setObject(i+1, obj[i]);
				}
			}
			//执行结果
			return ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//准备抛异常
			e.printStackTrace();
			throw new SysException(2001, "数据库异常，请检查数据库设置！");
		}finally {
			
			try {
				if(conn!=null) {
					conn.close();
				}
				if(ps!=null) {
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	/**
	 * 查询的方法
	 * @param sql：查询的语句
	 * @param obj：Object类型的数组
	 * @return List<Map<>>类型的集合
	 * @throws SysException 
	 */
	public List<Map<String, Object>> exeQuery(String sql,Object [] obj) throws SysException {
		//获得连接
		Connection conn = null;
		// 声明预编译对象
		PreparedStatement ps = null;
		// 声明结果集并初始化
		ResultSet rs = null;
		
		try {
			//获得连接
			conn = GetConn();
			// 从连接中获得预编译对象
			ps = conn.prepareStatement(sql);
			//创建List<Map<>>集合
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			//循环将参数赋值给字段，即列名
			for (int i = 0; i < obj.length; i++) {
				//将数组中的值循环赋给第i+1个字段
				ps.setObject(i+1, obj[i]);
			}
			//调用查询的方法，将结果集返回赋值给rs
			rs = ps.executeQuery();
			//从结果集中获得结构
			ResultSetMetaData rsmd = rs.getMetaData();
			//循环结果集
			while(rs.next()) {
				//创建map循环
				Map<String , Object> map = new HashMap<>();
				//将字段个数作为条件循环
				for(int i = 1;i<=rsmd.getColumnCount();i++) {
					//循环向集合中赋值，分别为结果集结构中的字段名和字段值
					map.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				//将map集合添加进list集合并返回
				list.add(map);
			}
			return list;
			
		} catch (SysException e) {
			// TODO Auto-generated catch block
			//准备抛异常
			throw new SysException(2001, "数据库异常，请检查数据库设置！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//准备抛异常
			throw new SysException(2001, "数据库异常，请检查数据库设置！");
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(ps!=null) {
					ps.close();
				}
				if(conn!=null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		DBUtil db = new DBUtil();
		Object [] obj = {};
		try {
			db.exeUpdate("insert into player values(2,'ssaass','ddssdd','ddddss','ssssdd',22);", obj);
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
