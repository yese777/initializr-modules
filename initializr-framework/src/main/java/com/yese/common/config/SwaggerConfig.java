package com.yese.common.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 张庆福
 * @description Swagger2配置类
 */
@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 系统基础配置
     */
    @Autowired
    private SystemConfig systemConfig;

    @Bean
    public Docket createRestApi(Environment environment) {
        // 设置dev和test环境开启Swagger(出于安全考虑在生产环境关闭swagger)
        Profiles profiles = Profiles.of("dev", "test");
        boolean flag = environment.acceptsProfiles(profiles);
        log.info("当前环境是否开启了Swagger:{}", flag);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(flag)
                .select()
                // RequestHandlerSelectors指定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.yese.controller"))
                // 过滤路径
                .paths(PathSelectors.any())
                .build();
    }

    // 构建api文档的详细信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title(systemConfig.getName() + "_接口文档")
                //作者信息
                .contact(new Contact(systemConfig.getDeveloper(), "https://github.com/yese777", "1321810135@qq.com"))
                //版本号
                .version(systemConfig.getVersion())
                //描述
                .description("简介...")
                .build();
    }

}