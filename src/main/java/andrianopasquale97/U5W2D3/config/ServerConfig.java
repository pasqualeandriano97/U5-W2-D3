package andrianopasquale97.U5W2D3.config;



import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ServerConfig {
    @Bean
    public Cloudinary cloudinaryUploader(@Value("${cloudinary.cloudName}") String name,
                                         @Value("${cloudinary.apiKey}") String key,
                                         @Value("${cloudinary.apiSecret}") String secret){
        Map<String, String> configuration = new HashMap<>();
        configuration.put("cloud_name",name);
        configuration.put("api_key",key);
        configuration.put("api_secret",secret);
        return new Cloudinary(configuration);
    }
}
