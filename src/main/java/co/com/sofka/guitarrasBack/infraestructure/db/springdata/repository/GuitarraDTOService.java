package co.com.sofka.guitarrasBack.infraestructure.db.springdata.repository;

import co.com.sofka.guitarrasBack.application.repository.GuitarraRepository;
import co.com.sofka.guitarrasBack.domain.Guitarra;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.GuitarraDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.stereotype.Service;
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
                .map(guitarra1-> modelMapper().map(guitarra1, Guitarra.class ));
    }
}
