package co.com.sofka.guitarsback.application.repository;

import co.com.sofka.guitarsback.domain.entity.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderRepository {
    Mono<Order> save(Order order);
    Flux<Order> findAll();
    Mono<Order> findById(String id);
}
