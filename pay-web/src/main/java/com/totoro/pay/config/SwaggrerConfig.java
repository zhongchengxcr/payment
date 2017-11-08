package com.totoro.pay.config;

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
 * swagger配置 . <br>
 * <p>
 * Copyright: Copyright (c) 2017/09/24 下午3:27
 * <p>
 *
 * @author zhongcheng_m@yeah.net
 * @version 1.0.0
 */
@EnableSwagger2
@Configuration
public class SwaggrerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.totoro.pay.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("第三方支付聚合微服务")
                .description("让支付变得如此简单")
                .termsOfServiceUrl("https://github.com/zhongchengxcr")
                .contact("程序猿zc")
                .version("1.0")
                .build();
    }

}
