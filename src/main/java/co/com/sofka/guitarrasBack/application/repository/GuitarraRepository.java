package co.com.sofka.guitarrasBack.application.repository;

import co.com.sofka.guitarrasBack.domain.Guitarra;
import reactor.core.publisher.Mono;

public interface GuitarraRepository {
    Mono<Guitarra> save(Guitarra guitarra);
}
