package com.jayqqaa12;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.route.AutoBindRoutes;


/**
 * API引导式配置
 */
public class MyConfig extends JFinalConfig {

	private boolean isDev = isDevMode();

	public static boolean isDevMode() {
		String osName = System.getProperty("os.name");
		return osName.indexOf("Windows") != -1;
	}

	static {
		if (isDevMode()) System.setProperty("LOGDIR", "c:/");
		else System.setProperty("LOGDIR", "/log");// linux
	}

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {

	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		// 自动扫描 建议用注解
		AutoBindRoutes abr = new AutoBindRoutes().autoScan(false);
		me.add(abr);
	}

	/**
	 * 
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {

	 
	}
	
 

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {

	 	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
	 
	}

	/**
	 * 使用Jrebel 关闭jetty扫描 设置扫描时间为0 但是jrebel 不用运行Myconfig 里面的方法 如果新增contrl model
	 * 之类的要重启一下 Jrebel 要使用 DEBUG 模式运行
	 */
	public static void main(String[] args) {

		JFinal.start("src/main/webapp", 2222, "/", 0);

	}

}
