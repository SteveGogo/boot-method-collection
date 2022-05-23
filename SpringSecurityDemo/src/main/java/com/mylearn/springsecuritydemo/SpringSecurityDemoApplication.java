//package com.mylearn.springsecuritydemo;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//
///**
// * https://blog.csdn.net/zzzgd_666/article/details/96444829
// * 五个handler一个filter两个User
// * 5个handler，分别是
// * 实现AuthenticationEntryPoint接口,当匿名请求需要登录的接口时,拦截处理
// * 实现AuthenticationSuccessHandler接口,当登录成功后,该处理类的方法被调用
// * 实现AuthenticationFailureHandler接口,当登录失败后,该处理类的方法被调用
// * 实现AccessDeniedHandler接口,当登录后,访问接口没有权限的时候,该处理类的方法被调用
// * 实现LogoutSuccessHandler接口,注销的时候调用
// *
// *
// */
//@SpringBootApplication
//public class SpringSecurityDemoApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(SpringSecurityDemoApplication.class, args);
//	}
//
//}
