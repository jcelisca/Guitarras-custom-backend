package co.com.sofka.guitarsback.infraestructure.config.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "co.com.sofka.guitarsback.infraestructure")
@EntityScan(basePackages = "co.com.sofka.guitarsback.domain")
class SpringBootService {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootService.class, args);
    }
}
