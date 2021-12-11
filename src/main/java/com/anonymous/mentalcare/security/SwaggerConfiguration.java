package com.anonymous.mentalcare.security;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.any())
                // 스웨거가 RestController를 전부 스캔을 한다.
                // basePackage => 어디를 범위로 스캔을 할 것인지 작성
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo()) // 여기까지가 정수님이 쓰신 거
                .forCodeGeneration(true)
                .ignoredParameterTypes(java.sql.Date.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(authorizationKey()))
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("익명의 멘탈케어")
                .description("익명의 사용자에게 고민을 털어놓고 위로의 말을 들어보세요.")
                .version("0.8.0")
                .termsOfServiceUrl("https://github.com/HanghaeAnonymous")
                .license("LICENSE")
                .licenseUrl("")
                .build();
    }

    /**
     * authorizationKey
     *
     * @return
     */
    private ApiKey authorizationKey() {
        return new ApiKey("JWT_TOKEN", "Authorization", "header");
    }

    /**
     * securityContext
     *
     * @return
     */
    private springfox.documentation.spi.service.contexts.SecurityContext securityContext() {

        return springfox.documentation.spi.service.contexts.SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    /**
     * defaultAuth
     *
     * @return
     */
    private List<SecurityReference> defaultAuth() {

        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        return Lists.newArrayList(new SecurityReference("JWT_TOKEN", authorizationScopes),
                new SecurityReference("HTTP_REQUEST", authorizationScopes));
    }
    // 완료가 되었으면 오른쪽 URL 로 접속 => http://localhost:8080/swagger-ui.html
}

