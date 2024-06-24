package com.eluanps.travelapp.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final ResponseMessage msg201 = customMessage201();
    private final ResponseMessage msg204put = customMessage204();
    private final ResponseMessage msg204delete = customMessage204D();
    private final ResponseMessage msg403 = customMessage403();
    private final ResponseMessage msg404 = customMessage404();
    private final ResponseMessage msg422 = customMessage422();
    private final ResponseMessage msg500 = customMessage500();

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(msg403, msg404, msg500))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(msg201, msg403, msg422, msg500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(msg204put, msg403, msg404, msg500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(msg204delete, msg403, msg404, msg500))
                
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eluanps.travelapp.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API do projeto Travel.io",
                "Esta API é utilizada no projeto Travel.io",
                "v1.0",
                "Sem termos por enquanto. ps: Se for utilizar algo do projeto fique a vontade, mas dê uma estrela ai e se possível credite no seu projeto, ok?!",
                new Contact("Edder Luan:", "https://github.com/edderluanps", ""),
                "Projeto sobre licença M.I.T.",
                "https://opensource.org/license/mit/",
                Collections.emptyList()
        );
    }

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }
    
    private ResponseMessage customMessage201(){
        
        Map<String, Header> map = new HashMap<>();
        map.put("location", new Header("location", "URI Do novo recurso", new ModelRef("string")));
        
        return new ResponseMessageBuilder()
                .code(201)
                .message("Recurso criado")
                .headersWithDescription(map).build();
    }
    
    private ResponseMessage customMessage204(){
        
        Map<String, Header> map = new HashMap<>();
        map.put("location", new Header("location", "URI Do novo recurso", new ModelRef("string")));
        
        return new ResponseMessageBuilder()
                .code(204)
                .message("Atualização OK")
                .headersWithDescription(map).build();
    }
    
    private ResponseMessage customMessage204D(){
        
        Map<String, Header> map = new HashMap<>();
        map.put("location", new Header("location", "URI Do novo recurso", new ModelRef("string")));
        
        return new ResponseMessageBuilder()
                .code(204)
                .message("Deleção OK")
                .headersWithDescription(map).build();
    }
    
    private ResponseMessage customMessage403(){
        
        Map<String, Header> map = new HashMap<>();
        map.put("location", new Header("location", "URI Do novo recurso", new ModelRef("string")));
        
        return new ResponseMessageBuilder()
                .code(403)
                .message("Não autorizado")
                .headersWithDescription(map).build();
    }    
    
    private ResponseMessage customMessage404(){
        
        Map<String, Header> map = new HashMap<>();
        map.put("location", new Header("location", "URI Do novo recurso", new ModelRef("string")));
        
        return new ResponseMessageBuilder()
                .code(404)
                .message("Não encontrado")
                .headersWithDescription(map).build();
    }     
    
    private ResponseMessage customMessage422(){
        
        Map<String, Header> map = new HashMap<>();
        map.put("location", new Header("location", "URI Do novo recurso", new ModelRef("string")));
        
        return new ResponseMessageBuilder()
                .code(422)
                .message("Erro de validação")
                .headersWithDescription(map).build();
    } 
    
    private ResponseMessage customMessage500(){
        
        Map<String, Header> map = new HashMap<>();
        map.put("location", new Header("location", "URI Do novo recurso", new ModelRef("string")));
        
        return new ResponseMessageBuilder()
                .code(500)
                .message("Erro inesperado")
                .headersWithDescription(map).build();
    }     
    
}
