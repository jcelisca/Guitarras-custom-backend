package co.com.sofka.guitarsback.application.repository;

import co.com.sofka.guitarsback.domain.entity.Guitar;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GuitarRepository {
    Mono<Guitar> save(Guitar guitar);
    Flux<Guitar> findAll();
    Flux<Guitar> findByTipo(String tipo);
    Flux<Guitar> findByMarca(String marca);
    Flux<Guitar> findByModelo(String modelo);
    Mono<Guitar> findById(String id);
    Flux<Guitar> saveAll(List<Guitar> guitarList);
}
