package co.com.sofka.guitarsback.infraestructure.db.springdata.repository;

import co.com.sofka.guitarsback.infraestructure.db.springdata.dto.GuitarDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface GuitarDataRepository extends ReactiveMongoRepository<GuitarDTO, String> {
    Flux<GuitarDTO> findByTipo(String tipo);
    Flux<GuitarDTO> findByMarca(String marca);
    Flux<GuitarDTO> findByModelo(String modelo);
}
