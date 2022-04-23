package co.com.sofka.guitarrasBack.infraestructure.config.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "co.com.sofka.guitarrasBack.infraestructure")
@EntityScan(basePackages = "co.com.sofka.guitarrasBack.domain")
public class SpringBootService {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootService.class, args);
    }
}
