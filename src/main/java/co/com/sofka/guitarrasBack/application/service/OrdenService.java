package co.com.sofka.guitarrasBack.application.service;

import co.com.sofka.guitarrasBack.application.repository.OrdenRepository;
import co.com.sofka.guitarrasBack.domain.Orden;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class OrdenService {

    private final OrdenRepository repository;

    public Mono<Orden> save(Orden orden){
        return repository.save(orden);
    }
}
