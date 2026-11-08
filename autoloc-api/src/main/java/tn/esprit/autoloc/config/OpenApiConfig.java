package tn.esprit.autoloc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AutoLoc REST API")
                        .version("1.0.0")
                        .description("API REST pour la gestion d'une agence de location de véhicules (AutoLoc). " +
                                "Supporte les opérations CRUD complètes avec DTOs, Bean Validation et gestion globale des erreurs.")
                        .contact(new Contact()
                                .name("AutoLoc Support - Website")
                                .email("support@autoloc.esprit.tn"))
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .servers(List.of(
                        new Server().url("http://localhost:8081").description("Serveur Local de Développement (Port 8081)"),
                        new Server().url("http://localhost:8080").description("Serveur Local Alternatif (Port 8080)")
                ));
    }
}
