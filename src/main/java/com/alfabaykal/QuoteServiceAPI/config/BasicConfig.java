package com.alfabaykal.QuoteServiceAPI.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BasicConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public OpenAPI socialMediaAPI() {

        Contact contact = new Contact();
        contact.setEmail("alfabaykal@gmail.com");
        contact.setName("Dmitrii Boiko");
        contact.setUrl("https://github.com/alfabaykal");

        Info info = new Info()
                .title("Quote Service API")
                .version("1.0.0")
                .contact(contact)
                .description("Simple RESTful API for quotes platform.");

        return new OpenAPI()
                .info(info);
    }
}
