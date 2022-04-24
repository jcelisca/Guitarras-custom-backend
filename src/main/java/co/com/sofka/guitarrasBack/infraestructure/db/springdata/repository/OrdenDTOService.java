package co.com.sofka.guitarrasBack.infraestructure.db.springdata.repository;

import co.com.sofka.guitarrasBack.application.repository.OrdenRepository;
import co.com.sofka.guitarrasBack.domain.Orden;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.OrdenDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

//@EnableReactiveMongoRepositories(basePackages = "co.com.sofka.guitarrasBack.infraestructure.db.springdata.repository")
@RequiredArgsConstructor
@Service
public class OrdenDTOService implements OrdenRepository {

    private final OrdenDataRepository repository;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public Mono<Orden> save(Orden orden) {
        return this.repository.save(
                modelMapper().map(orden, OrdenDTO.class)
        ).map(ordenDTO -> modelMapper().map(ordenDTO, Orden.class));
    }
}
