package com.vehicle.management.swagger.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(title = "Vehicle management", 
                              description = "Doing crud opertion"))

@SecurityScheme(name = "jwtauth", 
scheme = "Bearer", bearerFormat = "JWT", 
type = SecuritySchemeType.HTTP,
in = SecuritySchemeIn.HEADER)

@Configuration
public class OpenApiConfiguration {

//	@Bean
//	public OpenAPI openApi()
//	{
//		return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("bearer Authentication"))
//		.components(new Components().addSecuritySchemes("Bearer authentication",createAPIKeyScheme()));
//	}
//	private SecurityScheme createAPIKeyScheme() {
//	    return new SecurityScheme().type(SecurityScheme.Type.HTTP)
//	        .bearerFormat("JWT")
//	        .name("jwtauth")
//	        .scheme("bearer")
//	        
//	        ;
//	}
}
