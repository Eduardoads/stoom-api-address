package com.aeon.stoomapiaddress.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Arrays.asList(basicAuth()))
                .securityContexts(Arrays.asList(securityContext()))
                .apiInfo(this.ApiInfo().build());
    }
    private ApiInfoBuilder ApiInfo(){
        ApiInfoBuilder apiInfoBuilder =  new ApiInfoBuilder();
        apiInfoBuilder.title("Api Address");
        apiInfoBuilder.description("Api teste STOOM");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.termsOfServiceUrl("");
        apiInfoBuilder.contact(contato());
        return apiInfoBuilder;
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference("", authorizationScopes));
    }

    private BasicAuth basicAuth(){
        return new BasicAuth("");
    }

    private Contact contato(){
        return new Contact(
                "Acks Eduardo",
                "",
                "ackseduardo@gmail.com");
    }
}
