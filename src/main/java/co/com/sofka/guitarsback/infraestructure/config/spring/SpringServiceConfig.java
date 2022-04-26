package co.com.sofka.guitarsback.infraestructure.config.spring;

import co.com.sofka.guitarsback.application.repository.GuitarRepository;
import co.com.sofka.guitarsback.application.repository.OrderRepository;
import co.com.sofka.guitarsback.application.service.GuitarService;
import co.com.sofka.guitarsback.application.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SpringServiceConfig {
    @Bean
    public GuitarService guitarService(GuitarRepository repository){
        return new GuitarService(repository);
    }

    @Bean
    public OrderService orderService(OrderRepository repository){
        return new OrderService(repository);
    }
}
