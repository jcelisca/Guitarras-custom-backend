package co.com.sofka.guitarrasBack.infraestructure.config.spring;

import co.com.sofka.guitarrasBack.application.repository.GuitarraRepository;
import co.com.sofka.guitarrasBack.application.repository.OrdenRepository;
import co.com.sofka.guitarrasBack.application.service.GuitarraService;
import co.com.sofka.guitarrasBack.application.service.OrdenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SpringServiceConfig {
    @Bean
    public GuitarraService serviceGuitarra(GuitarraRepository repository){
        return new GuitarraService(repository);
    }

    @Bean
    public OrdenService serviceOrden(OrdenRepository repository){
        return new OrdenService(repository);
    }
}
