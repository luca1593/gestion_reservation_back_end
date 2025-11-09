package com.detech.gsrt.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
/*
    @Bean
    public GroupedOpenApi clientApi(){
        return GroupedOpenApi.builder()
                .group("client")
                .pathsToMatch("/reservation/**", "/avis/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi(){
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/tables/**", "/users/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicApi(){

        List<String> publicEndpoints = List.of(
                "/public",
                "/authenticate",
                "/users/save",
                "/v2/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/webjars/**",
                "/configuration/ui",
                "/configuration/security"
        );

        return GroupedOpenApi.builder()
                .group("puclic")
                .pathsToMatch(publicEndpoints.toArray(new String[0]))
                .build();
    }
*/
}
