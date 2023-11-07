<<<<<<< HEAD
package com.example.Plateforme_livraison.config;

=======


package com.example.Plateforme_livraison.config;



>>>>>>> 6d9d40dc9fbfc36fd49ea54ef8ac385d8b14697f
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
<<<<<<< HEAD
        registry.addMapping("/**") // Allow all paths
=======

        registry.addMapping("/") // Allow all paths
>>>>>>> 6d9d40dc9fbfc36fd49ea54ef8ac385d8b14697f
                .allowedOrigins("http://localhost:4200") // Specify your frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify the HTTP methods you want to allow
                .allowedHeaders("*"); // Allow all headers
    }
}
<<<<<<< HEAD
=======

>>>>>>> 6d9d40dc9fbfc36fd49ea54ef8ac385d8b14697f
