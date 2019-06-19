package com.cp.app.core.comm.security.config;

import com.cp.app.core.comm.security.authentiation.SystemAuthenticationEntryPoint;
import com.cp.app.core.comm.security.authentiation.SystemSystemAuthenticationProvider;
import com.cp.app.core.comm.security.authentiation.SystemUserDetailsService;
import com.cp.app.core.comm.security.component.SystemAccessDecisionManager;
import com.cp.app.core.comm.security.component.SystemFilterInvocationSecurityMetadataSource;
import com.cp.app.core.comm.security.filter.JWTAuthenticationFilter;
import com.cp.app.core.comm.security.filter.JWTAuthorizationFilter;
import com.cp.app.core.comm.security.handler.SystemAccessDeniedHandler;
import com.cp.app.core.comm.security.handler.SystemLogoutSuccessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName WebSecurityConfig
 * @Description TODO
 * @createdate 2019/2/14 星期四 14:12
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    /**
     * 需要放行的URL集合
     */
    private static final String[] AUTH_WHITELIST = {"/login.html"};
    @Autowired
    private SystemUserDetailsService systemUserDetailsService;
    //根据一个url请求，获得访问它所需要的roles权限
    @Autowired
    private SystemFilterInvocationSecurityMetadataSource systemFilterInvocationSecurityMetadataSource;
    //接收一个用户的信息和访问一个url所需要的权限，判断用户是否可以访问
    @Autowired
    private SystemAccessDecisionManager systemAccessDecisionManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 定义认证用户信息获取来源，密码校验规则等
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      auth.userDetailsService(systemUserDetailsService).passwordEncoder(passwordEncoder);
        auth.authenticationProvider(new SystemSystemAuthenticationProvider(systemUserDetailsService, passwordEncoder));//自定义身份校验组件
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);//配置过滤资源URL
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().accessDeniedHandler(new SystemAccessDeniedHandler())//403页面
                .and()
                .exceptionHandling().authenticationEntryPoint(new SystemAuthenticationEntryPoint()) //未授权处理器
                .and()

                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() { //URL配置安全策略
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        logger.info("配置安全策略");
                        object.setSecurityMetadataSource(systemFilterInvocationSecurityMetadataSource);
                        object.setAccessDecisionManager(systemAccessDecisionManager);
                        return object;
                    }
                })

                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager())) //登录过滤器
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), systemUserDetailsService)) //验证过滤器

                .logout() //默认注销行为为logout，可以通过下面的方式来修改
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/user/login")// 设置注销成功后跳转页面，默认是跳转到登录页面;
                .logoutSuccessHandler(new SystemLogoutSuccessHandler()) //注销成功后的处理
                .permitAll()
        ;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
