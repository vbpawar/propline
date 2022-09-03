package com.freelancer.RealEstate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
/**
 *
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.freelancer.RealEstate.*")
public class RealEstateApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RealEstateApplication.class, args);
    }

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Real Estate").apiInfo(apiInfo()).select()
                .paths(regex("/api.*")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Real Estate Service")
                .description("Sample Documentation Generateed Using SWAGGER2 for our Real Estate Rest API")
                .termsOfServiceUrl("https://www.google.com")
                .license("Real Estate")
                .licenseUrl("https://www.youtube.com/channel/UCORuRdpN2QTCKnsuEaeK-kQ").version("1.0").build();
    }

}
