package zjy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticSourceConfig extends WebMvcConfigurationSupport {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		//registry.addResourceHandler("/pic/**").addResourceLocations("C:/pic/");

		//WebMvcConfigurer.super.addResourceHandlers(registry);

	}

/*	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		if(!registry.hasMappingForPattern("/static/**")){
			registry.addResourceHandler("/static/**").addResourceLocations("C:/pic/");
		}
	}*/

}
