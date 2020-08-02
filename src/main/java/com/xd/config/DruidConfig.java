package com.xd.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

//    后台监控
    @Bean
    public ServletRegistrationBean startViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet()
                ,"/druid/*");
//        urlMappings表示druid访问后台的Controller

        HashMap<String,String> initParameters = new HashMap<>();

//        后台登录账号密码
        initParameters.put("loginUsername","root");
        initParameters.put("loginPassword","root");

//        谁可以访问
        initParameters.put("allow","");
        bean.setInitParameters(initParameters);
        return bean;

    }
}
