package pl.szymanski.projekt_inzynierski.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration for Swagger
 * @author Marek Szymański
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("pl.szymanski"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfoBuilder()
                .title("ProjektInzynierski - API")
                .description("API for soil temperature and moisture measurement (read, add sensor etc.)")
                .version("1.0")
                .contact(new springfox.documentation.service.Contact("Marek", "https://github.com/mass4", "marek.sz026@gmail.com"))
                .license("API License")
                .licenseUrl("https://github.com/mass4/ProjektInzynierski")
                .build();
    }
}
