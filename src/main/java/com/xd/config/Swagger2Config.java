package com.xd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket docket(Environment environment){
        //        在这里可以监测是否处于某项目环境下 需要在docket的传入参数中加入Environment environment参数
        Profiles profiles= Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Malloc")
                .apiInfo(getApiInfo())
                .enable(flag)
                .select()// this will automatically fall back to building the docket when the build method is called.
//                这里获取到了ApiSelectorBuilder对象
                .apis(RequestHandlerSelectors.basePackage("com.xd.controller"))
//                withMethodAnnotation 指定方法上的某注解会被Swagger检测到
//                withClassAnnotation   指定类上的某注解会被Swagger检测到
                .build()//building the docket when the build method is called.
                ;
    }
    public ApiInfo getApiInfo(){
        return new ApiInfo(
                "API文档",
                "Malloc的API文档",
                "v1.0",
                "localhost:8082/druid",
                new Contact("Malloc", "https://qm.qq.com/cgi-bin/qm/qr?k=_9vKIAd4nj9chFDXp6D0qelGw7gSlCoP&noverify=0", "Redis_set@163.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }
}
