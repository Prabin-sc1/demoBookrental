/*
package com.bookrental.bookrental.config;

// in order config app name definitions, we need some sort of configuration
//  we have to configure Docket

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docle api(){

        return new Docket(Documentation.SWAGGER_2)
                .apiInfo(getInfo())
                .select()
        apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo getInfo(){
        return new ApiInfo("name","desc","1.0", "Terms of service", new Contact("name","meail","website"),"license",Collections.emptyList());;
    }


}*/

// testing
// testing 2