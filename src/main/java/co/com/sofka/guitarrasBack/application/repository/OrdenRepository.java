package co.com.sofka.guitarrasBack.application.repository;

import co.com.sofka.guitarrasBack.domain.Orden;
import reactor.core.publisher.Mono;

public interface OrdenRepository {
    Mono<Orden> save(Orden orden);
}
