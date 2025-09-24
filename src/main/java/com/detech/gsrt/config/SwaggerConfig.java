package com.detech.gsrt.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luca
 */
@Configuration
public class SwaggerConfig {

    License license = new License();

    @Bean
    public OpenAPI springShopOpenAPI() {
        this.license.setName("DETECH Licence");
        this.license.setIdentifier("devtech");
        this.license.setUrl("devtech.licence.mg");
        return new OpenAPI()
                .info(new Info().title("GESTION DE RESERVATION API")
                        .description("Documentation pour l'API de Gestion de reservation")
                        .license(this.license)
                        .version("v1.0.0"));
    }
    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder().group("public").pathsToMatch("/**").build();
    }

}
