package com.king.controller;

import com.king.util.HttpUtil;
import com.king.util.KeyWordUtil;

public class Controller {

	public String loginIn(){
		String token = "";
		String url = KeyWordUtil.URL_loginIn
				.replace("UID", KeyWordUtil.UID)
				.replace("PWD", KeyWordUtil.PWD);
		
		String result = HttpUtil.doGet(url);
		if (!result.contains(KeyWordUtil.UID)) {
			System.out.println("loginIn:::"+result);
			return "error";
		}
		System.out.println("loginIn:::"+result);
		token = result.substring(KeyWordUtil.UID.length()+1);
		return token;
	}
	
	public String getMobilenum(String token){
		String mobile = "";
		String url = KeyWordUtil.URL_getMobilenum
				.replace("PID", KeyWordUtil.PID)
				.replace("UID", KeyWordUtil.UID)
				.replace("TOKEN", token)
				.replace("SIZE", KeyWordUtil.SIZE);
		String result = HttpUtil.doGet(url);
		if (!result.contains(token)) {
			System.out.println("getMobile:::"+result);
			return "error";
		}
		System.out.println("getMobile:::"+result);
		mobile = result.substring(0, 11);		
		return mobile;
	}
	
	public String getVcodeAndHoldMobilenum(String token,String mobile){
		String Vcode = "";
		String url = KeyWordUtil.URL_getVcodeAndHoldMobilenum
				.replace("UID", KeyWordUtil.UID)
				.replace("TOKEN", token)
				.replace("MOBILE", mobile)
				.replace("NEXT_PID", KeyWordUtil.PID)
				.replace("AUTHOR_UID", KeyWordUtil.AUTHOR_UID);
		String result = HttpUtil.doGet(url);
		if (!result.contains(token)) {
			System.out.println("getVcode:::"+result);
			return "error";
		}
		System.out.println("getVcode:::"+result);
		Vcode = result.substring(12, result.length()-token.length()-1);
		return Vcode;
	}
	
	public void addIgnoreList(String token,String mobile){
		String url = KeyWordUtil.URL_addIgnoreList
				.replace("UID", KeyWordUtil.UID)
				.replace("TOKEN", token)
				.replace("MOBILES", mobile)
				.replace("PID", KeyWordUtil.PID);
		String result = HttpUtil.doGet(url);
		System.out.println("addIgnoreList:::"+result);
	}
	
}
