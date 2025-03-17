package com.academico.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/**")
		   .allowedOrigins(
				"http://localhost:3000",
				"http://localhost:8080"
				).allowedHeaders(
			      HttpHeaders.AUTHORIZATION, 	   
				  HttpHeaders.ACCEPT,
				  HttpHeaders.CONTENT_TYPE
			    ).allowedMethods(
			      HttpMethod.GET.name(),
			      HttpMethod.POST.name(),
			      HttpMethod.PUT.name(),
			      HttpMethod.PATCH.name(),
			      HttpMethod.DELETE.name()
			    ).allowCredentials(false)
		         .maxAge(3600); 
	}

}
