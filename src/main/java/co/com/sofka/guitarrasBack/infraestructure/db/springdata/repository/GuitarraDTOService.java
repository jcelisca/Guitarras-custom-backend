package co.com.sofka.guitarrasBack.infraestructure.db.springdata.repository;

import co.com.sofka.guitarrasBack.application.repository.GuitarraRepository;
import co.com.sofka.guitarrasBack.domain.entity.Guitarra;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.GuitarraDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EnableReactiveMongoRepositories(basePackages = "co.com.sofka.guitarrasBack.infraestructure.db.springdata.repository")
@RequiredArgsConstructor
@Service
public class GuitarraDTOService implements GuitarraRepository {

    private final GuitarraDataRepository repository;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public Mono<Guitarra> save(Guitarra guitarra) {
        return repository.save(modelMapper().map(guitarra, GuitarraDTO.class))
                .map(guitarraDTO -> modelMapper().map(guitarraDTO, Guitarra.class));
    }

    @Override
    public Flux<Guitarra> findAll() {
        return repository.findAll()
                .map(guitarraDTO -> modelMapper().map(guitarraDTO, Guitarra.class));
    }

    @Override
    public Flux<Guitarra> findByTipo(String tipo) {
        return repository.findByTipo(tipo)
                .map(guitarraDTO -> modelMapper().map(guitarraDTO, Guitarra.class));
    }

    @Override
    public Flux<Guitarra> findByMarca(String marca) {
        return repository.findByMarca(marca)
                .map(guitarraDTO -> modelMapper().map(guitarraDTO, Guitarra.class));
    }

    @Override
    public Flux<Guitarra> findByModelo(String modelo) {
        return repository.findByModelo(modelo)
                .map(guitarraDTO -> modelMapper().map(guitarraDTO, Guitarra.class));
    }

    @Override
    public Mono<Guitarra> findById(String id) {
        return repository.findById(id)
                .map(guitarraDTO -> modelMapper().map(guitarraDTO, Guitarra.class));
    }

}
