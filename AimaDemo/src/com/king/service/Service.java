package com.king.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.king.controller.Controller;
import com.king.util.KeyWordUtil;

public class Service {

	private static String token = "";
	private static String operation = "1";
	private static final String loop = "1";
	private static final String stop = "2";
	private static final String next = "0";
	private static final String exit = "q";
	private static Controller controller = null;
	
	public static void main(String[] args) {
		BufferedReader strin = null;
		strin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("欢迎使用爱玛短信验证码接收平台");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("登录:::::");
		System.out.println("请输入账号，按Enter结束");
		try {
			KeyWordUtil.UID = strin.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("请输入密码，按Enter结束");
		try {
			KeyWordUtil.PWD = strin.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        controller = new Controller();
		String tokenTemp = controller.loginIn();
		if (!"error".equals(tokenTemp)) {
			token = tokenTemp;
			System.out.println("成功登录");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("请输入项目id，按Enter结束");
			try {
				KeyWordUtil.PID = strin.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(getMenu());
			System.out.println("将在20s后开始获取");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new GetInput().start();
			new GetMobileAndVcode().start();
		}else{
			System.out.println("登录失败，关闭后重试");
		}
		
	}
	
	public static String getMenu(){
		StringBuffer sb = new StringBuffer();
//		sb.append("输入1：开始获取手机号码\n");
		sb.append("输入0：获取下一个号码\n");
		sb.append("输入q：停止获取并退出系统\n");
		sb.append("输入？：调出此菜单\n");
		sb.append("按Enter结束输入\n");
		sb.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return sb.toString();
	}
	
	public static class GetInput extends Thread{

		@Override
		public void run() {
			BufferedReader strin = null;
			while(true){
				if (operation.equals("?") || operation.equals("？")) {
					System.out.println(getMenu());
					operation = "1";
				}
	            if (!operation.equals(exit)) {
	            	try {
		            	strin = new BufferedReader(new InputStreamReader(System.in));  
						operation = strin.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					System.out.println(">>>>>>>>>>>>>>>>>>>>");
					System.out.println(">>>>>感谢你的使用>>>>>>");
					System.out.println(">>>>>>>>>>>>>>>>>>>>");
					break;
				}
			}
			
		}
		
	}
	
	public static class GetMobileAndVcode extends Thread{

		@Override
		public void run() {
			String mobileTemp = "";
			String vcodeTemp = "";
			while (true) {
				if (operation.equals(loop) || operation.equals(next)) {
					mobileTemp = controller.getMobilenum(token);
					if (!"error".equals(mobileTemp)) {
						System.out.println("手机号码："+mobileTemp);
						try {
							sleep(20000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						while(true){
							
							if (operation.equals(loop)) {
								try {
									sleep(5000);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								vcodeTemp = controller.getVcodeAndHoldMobilenum(token, mobileTemp);
								if (!"error".equals(vcodeTemp)) {
									System.out.println("短信验证码："+vcodeTemp);
									try {
										sleep(20000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}else if(operation.equals(next)){
								controller.addIgnoreList(token, mobileTemp);
								operation = "1";
								break;
							}else if (operation.equals(exit)) {
								controller.addIgnoreList(token, mobileTemp);
								break;
							}
						}
					}
				}else{
					break;
				}
			}
			
		}
		
	}
}
