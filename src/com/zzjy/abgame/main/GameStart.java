package com.zzjy.abgame.main;

import java.text.DecimalFormat;

import com.zzjy.abgame.bean.GameBean;
import com.zzjy.abgame.bean.PlayerBean;
import com.zzjy.abgame.exception.AppException;
import com.zzjy.abgame.exception.SysException;
import com.zzjy.abgame.ui.Menu;
import com.zzjy.abgame.util.AdminXML;
import com.zzjy.abgame.util.Constants;
import com.zzjy.abgame.util.Input;

public class GameStart {
	// 玩家管理类的单例
	PlayersManager pm = PlayersManager.getInstance();
	// 管理员管理类的单例
	AdminManager am = AdminManager.getInstance();
	// 游戏管理类的单例
	GamesManager gm = GamesManager.getInstance();
	// XML读写方法的单例
	AdminXML ax = AdminXML.getInstance();
	// 小鸟管理类的单例
	BirdManager bm = BirdManager.getInstance();
	// 创建玩家信息的引用
	PlayerBean player;

	// 开始的方法
	public void start() {
		// 设定总开关
		boolean bo1 = true;
		// 总循环开始
		while (bo1) {
			// 总菜单显示
			Menu.displayMain();
			// 总菜单选择
			switch (Input.getInt("选项")) {
			// 玩家选项
			case 1:
				// 循环验证登陆3次
				for (int i = 0; i < ax.getLogintimes(); i++) {
					// 调用验证信息并将返回的玩家信息对象拿出备用
					try {
						player = pm.chkLogin(Input.getString("您的账号"), Input.getString("您的密码"));
					} catch (SysException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getErrMsg());
					}
					// 当对象为空时
					if (player == null) {
						// 当错误3次时提示
						if (i == ax.getLogintimes() - 1) {
							System.out.println("您输入错误次数过多，请稍后再试！");
						} else {
							// 否则提示剩余验证机会
							System.out.println("您输入的账号或密码不正确，请重新输入！您还有" + (Constants.LOGINTIMES - i - 1) + "次机会！");
						}
						// 当玩家信息有值，即成功返回对象时
					} else {
						// 设定玩家登陆后循环开关
						boolean bo2 = true;
						System.out.println("登陆成功！");
						// 开始循环
						while (bo2) {
							// 玩家菜单
							Menu.displayPlayer();
							// 玩家菜单选择
							switch (Input.getInt("您的选项")) {
							// 开始游戏
							case 1:

								// 调用游戏方法并将登陆获取的玩家信息作为实参赋值
								try {
									gm.playGame(player);
								} catch (SysException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getErrMsg());
								}

								break;
							// 查看成绩
							case 2:
								// 调用查询单个玩家的方法，当返回的数组不为null时
								try {
									if (gm.queryByLoginname(player) != null) {
										System.out.println("游戏时间\t\t\t得分");
										// 根据提示信息遍历返回的数组
										for (GameBean game : gm.queryByLoginname(player)) {
											// 当数组信息不为null时
											if (game != null) {
												// 输出游戏时间与分数
												System.out.println(game.getTime() + "\t" + game.getScore());
											}

										}
										// 返回的数组为null时备用
									} else {

									}
								} catch (SysException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getErrMsg());
								}

								break;
							// 返回上级
							case 0:
								// 关闭玩家登陆后开关
								bo2 = false;
								break;

							default:
								// 输入错误时提示
								System.out.println("请选择正确的选项！");
								break;
							}
						}
						// 将最大登陆次数赋值给循环次数，从而因超过条件而关闭外层验证循环
						i = ax.getLogintimes();
					}
				}

				break;
			// 管理员选项
			case 2:
				// 循环登陆管理员
				for (int i = 0; i < ax.getLogintimes(); i++) {
					// 判断管理员登陆正误
					if (!am.chkAdmin()) {
						// 当达到3次错误时跳出
						if (i == 2) {
							System.out.println("您输入的错误次数过多，请稍后再试！");
							break;
						} else {

						}
						// 提示剩余管理员验证机会
						System.out.println("您输入有误，请重新输入！您还有" + (ax.getLogintimes() - 1 - i) + "次机会");
					} else {
						// 纯秀
						System.out.println("欢迎光临！" + ax.getAdminname());
						// 设定管理员循环开关
						boolean bo21 = true;
						// 开启循环
						while (bo21) {
							// 管理员菜单：
							Menu.displayAdmin();
							// 选项选择
							switch (Input.getInt("选项")) {
							// 新增玩家
							case 1:

								// 调用新增玩家的方法
								try {
									pm.addPlayer(pm.addP());
									System.out.println("新建玩家成功！");
								} catch (SysException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getErrMsg());
								} catch (AppException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getErrMsg());
								}

								break;
							// 修改玩家
							case 2:

								try {
									// 提示信息
									System.out.println("序号\t" + "用户名\t" + "昵称\t"+"性别\t" + "年龄");
									// 调用查询所有的方法并遍历返回的玩家信息数组
									for (PlayerBean pb : pm.queryAll()) {
										// 信息数组不为null时
										if (pb != null) {
											// 输出可见信息
											System.out.println(pb.getId() + "、\t" + pb.getLoginname() + "\t"
													+ pb.getNickname() +"\t"+pb.getSex()+ "\t" + pb.getAge());
										} else {

										}

									}
									// 调用根据账号修改信息的方法
									if(pm.updatePlayer(Input.getInt("需要修改的玩家id，不想修改可跳过该项"))>0) {
										System.out.println("修改成功");
									}
								} catch (SysException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getErrMsg());
								} catch (AppException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getErrMsg());
								}

								break;
							// 删除玩家
							case 3:
								// 调用根据账号删除玩家的方法
								try {
									// 提示信息
									System.out.println("序号\t" + "用户名\t" + "昵称\t"+"性别\t" + "年龄");
									// 调用查询所有的方法并遍历返回的玩家信息数组
									for (PlayerBean pb : pm.queryAll()) {
										// 信息数组不为null时
										if (pb != null) {
											// 输出可见信息
											System.out.println(pb.getId() + "、\t" + pb.getLoginname() + "\t"
													+ pb.getNickname() +"\t"+pb.getSex()+ "\t" + pb.getAge());
										}
									}
									// 将方法返回的影响行数储存备用
									int dp = pm.deletePlayer(Input.getInt("要删除的玩家id"));
									// 根据返回值确定是否删除
									if (dp > 0) {
										System.out.println("删除成功");
									} else if (dp == 0) {
										System.out.println("没有删除");
									}
								} catch (SysException e1) {
									// TODO Auto-generated catch block
									System.out.println(e1.getErrMsg());
								}
								break;
							// 查询玩家
							case 4:

								try {
									// 提示信息
									System.out.println("序号\t" + "用户名\t" + "昵称\t"+"性别\t" + "年龄");
									// 调用查询所有的方法并遍历返回的玩家信息数组
									for (PlayerBean pb : pm.queryAll()) {
										// 信息数组不为null时
										if (pb != null) {
											// 输出可见信息
											System.out.println(pb.getId() + "\t" + pb.getLoginname() + "\t"
													+ pb.getNickname() + "\t"+pb.getSex()+"\t" + pb.getAge());
										} else {

										}

									}
								} catch (SysException e1) {
									// TODO Auto-generated catch block
									System.out.println(e1.getErrMsg());
								}

								break;
							// 查询游戏
							case 5:
								// 返回的游戏数组为null时
								try {
									// 将查询得到的所有游戏信息赋值给新集合

									// 新集合判空
									System.out.println("昵称\t游戏时间\t\t\t得分");
									// 遍历查询所有游戏返回的数组，从数据库取出的时间会带.0，所以截取前19位以去掉.0
									for (GameBean game : gm.queryAll()) {
										// 输出需要的信息
										System.out.println(game.getLoginname() + "\t" + game.getTime().substring(0, 19)
												+ "\t" + game.getScore());
									}

								} catch (SysException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getErrMsg());
								}
								break;
							// 分数统计
							case 6:
								try {
									// 判断是否查到游戏信息
									if (gm.queryByScore() != null) {
										DecimalFormat df = new DecimalFormat("#.00");
										System.out.println("用户名\t总局数\t游戏总得分\t幸运指数");
										for (int i2 = 0; i2 < gm.queryByScore().size(); i2++) {
											Double co = (double) gm.getCounts(gm.queryByScore().get(i2).getLoginname());
											Double sc = (double) gm.queryByScore().get(i2).getScore();
											String luck = df.format(sc/co);
											System.out.println(gm.queryByScore().get(i2).getLoginname() + "\t"
													+ gm.getCounts(gm.queryByScore().get(i2).getLoginname()) + "\t"
													+ gm.queryByScore().get(i2).getScore()+"\t\t"
													+ luck);
										}
									}
								} catch (SysException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getErrMsg());
								}

								break;
							// 参数设置
							case 7:

								// 设置参数管理总开关
								boolean set = true;
								// 设置参数管理循环
								while (set) {
									// 参数管理总菜单
									Menu.displayParameter();
									// 参数选项
									switch (Input.getInt("您的选项")) {
									// 修改第一只鸟
									case 1:
										bm.changeBird(1);
										break;
									// 修改第二只鸟
									case 2:
										bm.changeBird(2);
										break;
									// 修改第三只鸟
									case 3:
										bm.changeBird(3);
										break;
									case 4:
										// 展示并修改管理员登录名
										System.out.println("1、管理员登录名为：" + ax.getAdminname());
										am.updateAdminName();
										break;
									case 5:
										// 展示并修改管理员密码
										System.out.println("2、管理员密码为：" + ax.getAdminPassword());
										am.updateAdminPassword();
										break;
									case 6:
										// 展示并修改管理员最大登陆次数
										System.out.println("3、管理员登陆次数为：" + ax.getLogintimes());
										am.updateAdminLogintimes();
										break;
									case 0:
										// 关闭参数设置开关
										set = false;
										break;

									default:
										System.out.println("请输入正确的选项！");
										break;
									}
								}
								break;
							// 返回上级
							case 0:
								// 关闭管理员循环
								bo21 = false;
								break;
							default:
								System.out.println("请输入正确的选项！");
								break;
							}
						}
						// 将i赋最大值，从而关闭管理员循环
						i = ax.getLogintimes();
					}

				}
				break;
			case 0:
				System.out.println("游戏结束……");
				// 关闭主循环
				bo1 = false;
				break;

			default:
				System.out.println("请输入正确的选项！");
				break;
			}
		}

	}
}
