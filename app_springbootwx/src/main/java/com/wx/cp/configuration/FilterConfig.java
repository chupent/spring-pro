package com.wx.cp.configuration;

import com.wx.cp.configuration.filter.WeChatAuthorizationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName FilterConfig
 * @Description TODO 过滤器配置
 * 拓展:过滤器配置二
 * 1、在Filter子类上使用@WebFilter注解（servlet3.0规范）
 * 2、在Application使用@ServletComponetScan指定Filter子类所在的包
 * 特点:
 * 1、@WebFilter优先过滤顺序>FilterRegistrationBean
 * 2、@WebFilter优先过滤Filter类名的字母顺序倒序排列
 *
 * @createdate 2019/4/4 星期四 16:51
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registerFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();//注册过滤器
        filterRegistrationBean.setFilter(new WeChatAuthorizationFilter());//设置微信认证过滤器
        filterRegistrationBean.addUrlPatterns("/*");//添加拦截URL
        filterRegistrationBean.setName("WeiXinAuthorizationFilter");//设置过滤器名称
        filterRegistrationBean.setOrder(1);//设置过滤顺序
        return filterRegistrationBean;
    }
}
