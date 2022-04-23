package co.com.sofka.guitarrasBack.infraestructure.db.springdata.repository;

import co.com.sofka.guitarrasBack.domain.Guitarra;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.GuitarraDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface GuitarraDataRepository extends ReactiveMongoRepository<GuitarraDTO, String> {
    Flux<GuitarraDTO> findByTipo(String tipo);
    Flux<GuitarraDTO> findByMarca(String marca);
    Flux<GuitarraDTO> findByModelo(String modelo);
}
