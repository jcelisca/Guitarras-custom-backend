package co.com.sofka.guitarrasBack.application.repository;

import co.com.sofka.guitarrasBack.domain.Guitarra;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GuitarraRepository {
    Mono<Guitarra> save(Guitarra guitarra);
    Flux<Guitarra> findAll();
    Flux<Guitarra> findByTipo(String tipo);
    Flux<Guitarra> findByMarca(String marca);
}
