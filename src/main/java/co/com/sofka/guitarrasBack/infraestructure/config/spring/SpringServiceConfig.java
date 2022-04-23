package co.com.sofka.guitarrasBack.infraestructure.config.spring;

import co.com.sofka.guitarrasBack.application.repository.GuitarraRepository;
import co.com.sofka.guitarrasBack.application.service.GuitarraService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringServiceConfig {

    @Bean
    public GuitarraService service(GuitarraRepository repository){
        return new GuitarraService(repository);
    }
}
