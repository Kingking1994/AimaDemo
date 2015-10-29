package com.king.util;

public class KeyWordUtil {
	
	//用户id
	public static String UID = "";
	//用户密码
	public static String PWD = "";
	//项目id
	public static String PID = "";
	//获取号码数
	public static String SIZE = "1";
	//开发者id
	public static final String AUTHOR_UID = "kingking1994";
	
	public static final String URL_loginIn = "http://api.f02.cn/http.do?action=loginIn&uid=UID&pwd=PWD";
	public static final String URL_getMobilenum = "http://api.f02.cn/http.do?action=getMobilenum&pid=PID&uid=UID&token=TOKEN&size=SIZE";
	public static final String URL_getVcodeAndHoldMobilenum = "http://api.f02.cn/http.do?action=getVcodeAndHoldMobilenum&uid=UID&token=TOKEN&mobile=MOBILE&next_pid=NEXT_PID&author_uid=AUTHOR_UID";
	public static final String URL_addIgnoreList = "http://api.f02.cn/http.do?action=addIgnoreList&uid=UID&token=TOKEN&mobiles=MOBILES&pid=PID";


}
