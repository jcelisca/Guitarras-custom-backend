package co.com.sofka.guitarrasBack.application.service;


import co.com.sofka.guitarrasBack.application.repository.GuitarraRepository;
import co.com.sofka.guitarrasBack.domain.Guitarra;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GuitarraService {
    private final GuitarraRepository repository;

    public Mono<Guitarra> save(Guitarra guitarra){
        return repository.save(guitarra);
    }

    public Flux<Guitarra> findAll(){
        return repository.findAll();
    }

    public Flux<Guitarra> findByTipo(String tipo){
        return repository.findByTipo(tipo);
    }

    public Flux<Guitarra> findByMarca(String marca){
        return repository.findByMarca(marca);
    }

    public Flux<Guitarra> findByModelo(String modelo){
        return repository.findByModelo(modelo);
    }


}
