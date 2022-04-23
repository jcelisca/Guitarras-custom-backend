package co.com.sofka.guitarrasBack.application.service;


import co.com.sofka.guitarrasBack.application.repository.GuitarraRepository;
import co.com.sofka.guitarrasBack.domain.Guitarra;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GuitarraService {
    private final GuitarraRepository repository;

    public Mono<Guitarra> save(Guitarra guitarra){
        return repository.save(guitarra);
    }
}
