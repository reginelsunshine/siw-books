package it.uniroma3.siw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configura l'accesso alla cartella "uploads"
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:C:/myapp/uploads/");
    }
}
