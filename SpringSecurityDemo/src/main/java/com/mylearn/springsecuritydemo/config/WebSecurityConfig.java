//package com.mylearn.springsecuritydemo.config;
//
//import com.mylearn.springsecuritydemo.auth.CustomerJwtAuthenticationTokenFilter;
//import com.mylearn.springsecuritydemo.auth.CustomerUserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)// 控制@Secured权限注解
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    /**
//     * 这里需要交给spring注入,而不是直接new
//     */
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private CustomerUserDetailService customerUserDetailService;
//    @Autowired
//    private CustomerAuthenticationFailHandler customerAuthenticationFailHandler;
//    @Autowired
//    private CustomerAuthenticationSuccessHandler customerAuthenticationSuccessHandler;
//    @Autowired
//    private CustomerJwtAuthenticationTokenFilter customerJwtAuthenticationTokenFilter;
//    @Autowired
//    private CustomerRestAccessDeniedHandler customerRestAccessDeniedHandler;
//    @Autowired
//    private CustomerLogoutSuccessHandler customerLogoutSuccessHandler;
//    @Autowired
//    private CustomerAuthenticationEntryPoint customerAuthenticationEntryPoint;
//
//
//
//    /**
//     * 该方法定义认证用户信息获取的来源、密码校验的规则
//     *
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //auth.authenticationProvider(myauthenticationProvider)  自定义密码校验的规则
//
//        //如果需要改变认证的用户信息来源，我们可以实现UserDetailsService
//        auth.userDetailsService(customerUserDetailService).passwordEncoder(passwordEncoder);
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        /**
//         * antMatchers: ant的通配符规则
//         * ? 匹配任何单字符
//         * * 匹配0或者任意数量的字符，不包含"/"
//         * ** 匹配0或者更多的目录，包含"/"
//         */
//        http
//                .headers()
//                .frameOptions().disable();
//
//        http
//                //登录后,访问没有权限处理类
//                .exceptionHandling().accessDeniedHandler(customerRestAccessDeniedHandler)
//                //匿名访问,没有权限的处理类
//                .authenticationEntryPoint(customerAuthenticationEntryPoint)
//        ;
//
//        //使用jwt的Authentication,来解析过来的请求是否有token
//        http
//                .addFilterBefore(customerJwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//
//
//        http
//                .authorizeRequests()
//                //这里表示"/any"和"/ignore"不需要权限校验
//                .antMatchers("/ignore/**", "/login", "/**/register/**").permitAll()
//                .anyRequest().authenticated()
//                // 这里表示任何请求都需要校验认证(上面配置的放行)
//
//
//                .and()
//                //配置登录,检测到用户未登录时跳转的url地址,登录放行
//                .formLogin()
//                //需要跟前端表单的action地址一致
//                .loginProcessingUrl("/login")
//                .successHandler(customerAuthenticationSuccessHandler)
//                .failureHandler(customerAuthenticationFailHandler)
//                .permitAll()
//                //配置取消session管理,又Jwt来获取用户状态,否则即使token无效,也会有session信息,依旧判断用户为登录状态
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                //配置登出,登出放行
//                .and()
//                .logout()
//                .logoutSuccessHandler(customerLogoutSuccessHandler)
//                .permitAll()
//                .and()
//                .csrf().disable()
//        ;
//    }
//
//
//}
