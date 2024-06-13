package com.example.ravi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        servers = {
                @Server(
                        url = "http://172.17.17.42:8090",
                        description = "Development Server - Local IP"
                ),
                @Server(
                        url = "http://154.118.227.229:8050",
                        description = "Production Server - Public IP"
                ),
                @Server(
                        url = "https://status.kakakuona.tz",
                        description = "Production Server - Domain"
                )
        },
        info = @Info(
                title = "Oxygen Status API",
                version = "v1",
                description = "Oxygen Status RESTful API for Mobile Apps Android & iOS\n" +
                        "\nContacts:\n",
//                        "- Joyce Kimata: [LinkedIn](https://www.linkedin.com/in/iamsammysd/)\n",
                license = @License(
                        name = "MIT License",
                        url = "https://github.com/bchen04/springboot-swagger-rest-api/blob/master/LICENSE"
                )
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
}
