package co.com.sofka.guitarsback.application.service;


import co.com.sofka.guitarsback.application.repository.GuitarRepository;
import co.com.sofka.guitarsback.domain.entity.Guitar;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class GuitarService {
    private final GuitarRepository repository;

    public Mono<Guitar> save(Guitar guitar){
        return repository.save(guitar);
    }
    public Flux<Guitar> saveAll(List<Guitar> guitarList){
        return repository.saveAll(guitarList);
    }

    public Flux<Guitar> findAll(){
        return repository.findAll();
    }

    public Flux<Guitar> findByTipo(String tipo){
        return repository.findByTipo(tipo);
    }

    public Flux<Guitar> findByMarca(String marca){
        return repository.findByMarca(marca);
    }

    public Flux<Guitar> findByModelo(String modelo){
        return repository.findByModelo(modelo);
    }

    public Mono<Guitar> findById(String id){
        return repository.findById(id);
    }

}
