package com.shawn.chapter5.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean
    public TestRestTemplate TestRestTemplate() {
        return new TestRestTemplate();
    }

    /**
     * 配置Druid的监控
     * 配置一个管理后台的Servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),
                "/druid/*");
        Map<String,String> initParams = new HashMap<>();
        {
            //登陆名密码
            initParams.put("loginUsername","admin");
            initParams.put("loginPassword","1234");
            //白名单
            initParams.put("allow","localhost");
            //黑名单
            initParams.put("deny","");
        }
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 配置web监控的拦截器
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        {
            registrationBean.setFilter(new WebStatFilter());
            //配置属型
            Map<String,String> initParam = new HashMap<>();
            {
                //拦截白名单
                initParam.put("exclusions","*.js,*.css");
            }
            registrationBean.setInitParameters(initParam);
            //拦截配置 /* 拦截所有
            registrationBean.setUrlPatterns(Arrays.asList("/*"));
        }
        return registrationBean;
    }

}
