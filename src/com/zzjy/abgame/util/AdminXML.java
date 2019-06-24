package com.zzjy.abgame.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.zzjy.abgame.bean.AdminBean;
import com.zzjy.abgame.main.AdminManager;

public class AdminXML {
	//单例化
	private static AdminXML ax = new AdminXML();
	private AdminXML() {
		
	}
	public static AdminXML getInstance() {
		return ax;
	}
	//修改管理员名称的方法
	public boolean updateAdminname(String adminname) {
		//定义解析器
		SAXReader sr = new SAXReader();
		//获取文件
		File file = new File("admins.xml");
		//创建文档
		Document doc;
		try {
			//使用解析器读取文档
			doc = sr.read(file);
			//获取根节点
			Element eles = doc.getRootElement();
			//获取子节点并修改子节点的属性值
			eles.element("loginname").setText(adminname);
			//将文档写入文件
			this.writer(file, doc);
			//写入成功返回true
			return true;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			System.out.println("文档修改出现问题，请检查XML设置！");
		}
		//修改失败返回false
		return false;
		
		
		
	}
	
	/**
	 * 修改管理员密码的方法
	 * @param adminPassword
	 * @return
	 */
	public boolean updatePassword(String adminPassword){
		//创建解析器
		SAXReader sr = new SAXReader();
		//读取文件
		File file = new File("admins.xml");
		try {
			//将文件用解析器解析为文档
			Document doc = sr.read(file);
			//获取根节点
			Element eles = doc.getRootElement();
			//获取子节点并修改属性值
			eles.element("password").setText(adminPassword);
			//将文档写入文件
			this.writer(file, doc);
			//写入后返回true
			return true;
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//报异常后返回false
		return false; 
	}
	
	/**
	 * 修改最大登陆次数
	 * @param logintimes
	 * @return
	 */
	public boolean updateLogintimes(String logintimes) {
		//创建解析器
		SAXReader sr = new SAXReader();
		//获取文件
		File file = new File("admins.xml");
		try {
			//将文件读取为文档
			Document doc = sr.read(file);
			//获取根节点
			Element eles = doc.getRootElement();
			//获取子节点，并修改属性值
			eles.element("logintimes").setText(logintimes);
			//将文档写入文件
			this.writer(file, doc);
			//写入后返回true
			return true;
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//否则返回false
		return false; 
	}
	
	/**
	 * 获取管理员名称的方法
	 * @return
	 */
	public String getAdminname() {
		//创建解析器
		SAXReader sr = new SAXReader();
		//获取文件
		File file = new File("admins.xml");
		try {
			//将文件读取为文档
			Document doc = sr.read(file);
			//获取根节点
			Element eles = doc.getRootElement();
			//获取子节点，并修改属性值
			String s1 = eles.element("loginname").getText();
			//返回获取到的属性值
			return s1;
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//没有获取到，返回null
		return null;
		
	}
	
	/**
	 * 获取管理员密码
	 * @return
	 */
	public String getAdminPassword() {
		//创建解析器
		SAXReader sr = new SAXReader();
		//获取文件
		File file = new File("admins.xml");
		try {
			//将文件读取为文档
			Document doc = sr.read(file);
			//获取根节点
			Element eles = doc.getRootElement();
			//获取子节点，并修改属性值
			String s1 = eles.element("password").getText();
			//返回获取到的密码
			return s1;
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//没有获取到则返回null
		return null;
		
	}
	
	/**
	 * 获取最大登陆次数
	 * @return
	 */
	public int getLogintimes() {
		//创建解析器
		SAXReader sr = new SAXReader();
		//获取文件
		File file = new File("admins.xml");
		try {
			//将文件读取为文档
			Document doc = sr.read(file);
			//获取根节点
			Element eles = doc.getRootElement();
			//获取子节点，并修改属性值
			int s1 = Integer.parseInt(eles.element("logintimes").getText());
			//返回获取到的值
			return s1;
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//没有获取到则返回0
		return 0;
		
	}
	
	/**
	 * 写入XML的重复过程，封装为一个方法
	 * @param file
	 * @param doc
	 */
	public void writer(File file,Document doc) {
		//设置输出格式
		OutputFormat of = OutputFormat.createPrettyPrint();
		//设置格式编码
		of.setEncoding("utf-8");
		//创建写入类的对象
		XMLWriter xw;
		try {
			//写入文件的方法作为参数，并设置格式
			xw = new XMLWriter(new FileWriter(file),of);
			//写入文档
			xw.write(doc);
			//压入缓冲
			xw.flush();
			//关闭资源
			xw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/*public static void main(String[] args) {
		AdminXML ax = new AdminXML();
		ax.updateAdminname("gsdsfdsfhd");
	}*/
}
