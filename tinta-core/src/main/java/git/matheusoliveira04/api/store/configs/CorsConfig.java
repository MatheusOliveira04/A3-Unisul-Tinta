package git.matheusoliveira04.api.store.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Libera absolutamente todas as rotas da API
                .allowedOrigins("http://127.0.0.1:5500", "http://localhost:5500") // Origem do seu Live Server
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
                .allowedHeaders("*") // Aceita qualquer cabeçalho enviado pelo navegador
                .allowCredentials(true);
    }
}
