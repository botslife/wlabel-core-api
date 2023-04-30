package org.tm.api.core.common.documentation;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@SecurityScheme(name="Authorization", scheme="bearer", type= SecuritySchemeType.APIKEY,in= SecuritySchemeIn.HEADER,bearerFormat = "JWT")
public class SwaggerConfiguration {

    public static final String AUTHORIZATION_HEADER = "Authorization ";
    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Lotus Core API")
                        .description("Lotus Core API's for TM")
                        .version("v0.0.1")
                        .license(new License().name("Reach us for License").url("https://uk.tm.org/transcendental-meditation-contact")))
                .externalDocs(new ExternalDocumentation()
                        .description("Our country specific pages")
                        .url("https://uk.tm.org/choose-your-country"));
    }
}