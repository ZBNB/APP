package com.util;

import org.springframework.context.ApplicationContext;

public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_USER_RIGHTS = "sessionUserRights";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String NO_INTERCEPTOR_PATH = ".*/((css)|(images)|(js)|(login)|(logout)|(code)|(HttpAPIController)).*";	//不对匹配该值的访问路径拦截（正则）
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	
	public static final String HTTP_API_ALIDAYU = "alidayu";
	public static final String HTTP_API_WEB_CHINESE = "webChinese";
	public static final Integer SYSTEM_ROLE_ID = 1;//系统管理员角色id
}
