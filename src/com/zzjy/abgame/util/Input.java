package com.zzjy.abgame.util;

import java.util.Scanner;

public final class Input {
	
	/**
	 * 这是一个控制台输入的方法
	 * @param s
	 * @return
	 */
	public static String getString(String s) {
		//输出提示信息
		System.out.println("请输入"+s+"：");
		//获取控制台
		Scanner sc2 = new Scanner(System.in);
		//获取String类型信息并返回
		return sc2.next();
	}
	
	/**
	 * 即使输入的是空串或空格也会获取的方法
	 * @param s
	 * @return
	 */
	public static String getNullString(String s) {
		//输出提示信息
		System.out.println("请输入"+s+"：");
		//获取控制台
		Scanner sc2 = new Scanner(System.in);
		//获取String类型信息并返回
		return sc2.nextLine().trim();
	}
	
	/**
	 * 获取控制台输入的数字方法
	 * @param s
	 * @return
	 */
	public static int getInt(String s) {
		//设定主开关
		boolean boo = true;
		//设定数字初始化
		int i1 = 0;
		//开启循环
		while(boo) {
			try {
				//提示信息
				System.out.println("请输入"+s+"：");
				//获取控制台输入
				Scanner sc5 = new Scanner(System.in);
				//获取int类型的控制台
				i1 = sc5.nextInt();
				//关闭循环
				boo = false;
			//当出现异常时提示
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("您的输入有误，请输入正确的数字！");
			}
		}
		//返回获取到的数字
		return i1;
		
		
	}
}
