package Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map uploads folder to actual physical location
        System.out.println("Configuring resource handlers for uploads");
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:src/main/resources/static/uploads/")
                .setCachePeriod(0); // Disable caching for uploads

        // Make sure static resources are properly mapped
        registry.addResourceHandler("/Images/**")
                .addResourceLocations("classpath:/static/Images/")
                .setCachePeriod(0); // Disable caching for images

        System.out.println("Resource handlers configured");
    }
}