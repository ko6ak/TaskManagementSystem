package com.example.task_management_system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${taskman.url}")
    private String url;

    @Bean
    public OpenAPI myOpenAPI() {
        Server server = new Server();
        server.setUrl(url);
        server.setDescription("Server URL");

        Contact contact = new Contact();
        contact.setEmail("denis.tkachenko.369@gmail.com");
        contact.setName("Denis Tkachenko");

        Info info = new Info()
                .title("Task Management System")
                .version("1.0")
                .contact(contact);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}