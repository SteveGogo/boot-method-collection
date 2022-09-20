package com.mylearn.springsecuritydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * https://blog.csdn.net/I_am_Hutengfei/article/details/100561564#t18
 * https://blog.csdn.net/qq_43799161/article/details/123854833?spm=1001.2101.3001.6650.5&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-5-123854833-blog-100561564.pc_relevant_multi_platform_whitelistv4&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-5-123854833-blog-100561564.pc_relevant_multi_platform_whitelistv4&utm_relevant_index=10
 * https://gitee.com/lihaJay/ssj
 * 五个handler一个filter两个User
 * 5个handler，分别是
 * 实现AuthenticationEntryPoint接口,当匿名请求需要登录的接口时,拦截处理
 * 实现AuthenticationSuccessHandler接口,当登录成功后,该处理类的方法被调用
 * 实现AuthenticationFailureHandler接口,当登录失败后,该处理类的方法被调用
 * 实现AccessDeniedHandler接口,当登录后,访问接口没有权限的时候,该处理类的方法被调用
 * 实现LogoutSuccessHandler接口,注销的时候调用
 */
@SpringBootApplication
public class SpringSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}

}
