package uk.ac.gcu.notes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("Honours Project API")
        .apiInfo(apiInfo()).select()
        .apis(RequestHandlerSelectors.basePackage("uk.ac.gcu.notes"))
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfo("Honours Project Notes API", "Endpoints for the Notes Service api project developed as part of"
        + " my honours project for GCU's computing program.", "1.0", "Private Use", new Contact("Michael Suleiman",
        null, "mosuleiman19@gmail.com"), "License of API",
        "API license URL", Collections.emptyList());
  }
}
