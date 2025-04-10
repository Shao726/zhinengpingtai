package com.dite.znpt.config;


import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        RequestParameterBuilder parameterBuilder = new RequestParameterBuilder();
        List<RequestParameter> parameters = new ArrayList<>();
        parameterBuilder.name("Authorization")
                .description("token值")
                .in(ParameterType.HEADER)
//                todo 暂时设置为false，后续上登录后需要开启
                .required(false)
                .build();
        parameters.add(parameterBuilder.build());
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(true)//开启Swagger文档
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dite"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(parameters);
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("智能平台")
                .version("1.0")
                .build();
    }
}