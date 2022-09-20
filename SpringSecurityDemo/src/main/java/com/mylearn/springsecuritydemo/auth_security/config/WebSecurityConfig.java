package com.mylearn.springsecuritydemo.auth_security.config;

import com.mylearn.springsecuritydemo.auth_security.LoginOutHandler.*;
import com.mylearn.springsecuritydemo.auth_security.access.CustomerAccessDeniedHandler;
import com.mylearn.springsecuritydemo.auth_security.access.CustomizeAbstractSecurityInterceptor;
import com.mylearn.springsecuritydemo.auth_security.access.CustomizeAccessDecisionManager;
import com.mylearn.springsecuritydemo.auth_security.access.CustomizeFilterInvocationSecurityMetadataSource;
import com.mylearn.springsecuritydemo.auth_security.userDetailInfo.CustomerUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CustomerAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private CustomerAuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private CustomerAuthenticationFailHandler authenticationFailHandler;
    @Resource
    private CustomerLogoutSuccessHandler logoutSuccessHandler;
    @Resource
    private CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    @Resource
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;

    @Resource
    private CustomizeAccessDecisionManager accessDecisionManager;
    @Resource
    private CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;
    @Resource
    private CustomizeAbstractSecurityInterceptor securityInterceptor;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new CustomerUserDetailService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    /**
     * 不走过滤器链放行
     * 前端页面的静态资源放行
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico", "/verifyCode");
    }


    /**
     * 走 Spring Security 过滤器链，在过滤器链中，给请求放行
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests().
                //antMatchers("/getUser").hasAuthority("query_user").
                //antMatchers("/**").fullyAuthenticated().
                        withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(accessDecisionManager);//决策管理器
                        o.setSecurityMetadataSource(securityMetadataSource);//安全元数据源
                        return o;
                    }
                }).
                //登出
                        and().logout().
                permitAll().//允许所有用户
                logoutSuccessHandler(logoutSuccessHandler).//登出成功处理逻辑
                deleteCookies("JSESSIONID").//登出之后删除cookie
                //登入
                        and().formLogin().
                permitAll().//允许所有用户
                successHandler(authenticationSuccessHandler).//登录成功处理逻辑
                failureHandler(authenticationFailHandler).//登录失败处理逻辑
                //异常处理(权限拒绝、登录失效等)
                        and().exceptionHandling().
                accessDeniedHandler(customerAccessDeniedHandler).//权限拒绝处理逻辑
                authenticationEntryPoint(authenticationEntryPoint).//匿名用户访问无权限资源时的异常处理
                //会话管理
                        and().sessionManagement().
                maximumSessions(1).//同一账号同时登录最大用户数
                expiredSessionStrategy(sessionInformationExpiredStrategy);//会话失效(账号被挤下线)处理逻辑
        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
    }


}
