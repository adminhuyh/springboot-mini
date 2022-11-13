package com.zyxx.common.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2 配置
 *
 * @Author Lizhou
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 扫描包位置
//                .apis(RequestHandlerSelectors.basePackage("com.zyxx.sys"))
//                .apis(RequestHandlerSelectors.basePackage("com.zyxx.api"))
                .apis(Predicates.or(RequestHandlerSelectors.basePackage("com.zyxx.sys"),RequestHandlerSelectors.basePackage("com.zyxx.xcx")))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("综合管理系统 后台服务API接口文档")
                // 简介
                .description("综合管理系统 后台服务API接口文档")
                // 更新服务条款url
                .termsOfServiceUrl("http://localhost:8080/")
                // 作者
                .contact("hotdog")
                // 版本
                .version("1.0.0")
                // 构建
                .build();
    }
}