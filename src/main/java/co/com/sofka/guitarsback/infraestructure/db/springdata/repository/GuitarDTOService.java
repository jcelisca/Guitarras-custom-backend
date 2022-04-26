package co.com.sofka.guitarsback.infraestructure.db.springdata.repository;

import co.com.sofka.guitarsback.application.repository.GuitarRepository;
import co.com.sofka.guitarsback.domain.entity.Guitar;
import co.com.sofka.guitarsback.infraestructure.db.springdata.dto.GuitarDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EnableReactiveMongoRepositories(basePackages = "co.com.sofka.guitarsback.infraestructure.db.springdata.repository")
@RequiredArgsConstructor
@Service
public class GuitarDTOService implements GuitarRepository {

    private final GuitarDataRepository repository;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public Mono<Guitar> save(Guitar guitar) {
        return repository.save(modelMapper().map(guitar, GuitarDTO.class))
                .map(guitarraDTO -> modelMapper().map(guitarraDTO, Guitar.class));
    }

    @Override
    public Flux<Guitar> findAll() {
        return repository.findAll()
                .map(guitarraDTO -> modelMapper().map(guitarraDTO, Guitar.class));
    }

    @Override
    public Flux<Guitar> findByTipo(String tipo) {
        return repository.findByTipo(tipo)
                .map(guitarraDTO -> modelMapper().map(guitarraDTO, Guitar.class));
    }

    @Override
    public Flux<Guitar> findByMarca(String marca) {
        return repository.findByMarca(marca)
                .map(guitarraDTO -> modelMapper().map(guitarraDTO, Guitar.class));
    }

    @Override
    public Flux<Guitar> findByModelo(String modelo) {
        return repository.findByModelo(modelo)
                .map(guitarraDTO -> modelMapper().map(guitarraDTO, Guitar.class));
    }

    @Override
    public Mono<Guitar> findById(String id) {
        return repository.findById(id)
                .map(guitarraDTO -> modelMapper().map(guitarraDTO, Guitar.class));
    }

}
