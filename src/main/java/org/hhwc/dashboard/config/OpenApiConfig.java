package org.hhwc.dashboard.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Value("${dashboard.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI dashboardOpenApi() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server in development environment");

        Info apiDoc = new Info()
                .title("API Documentation of Dashboard")
                .version("0.1.0")
                .description("Backend API of students and teachers");

        return new OpenAPI().info(apiDoc).servers(List.of(devServer));
    }
}
