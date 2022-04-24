package co.com.sofka.guitarrasBack.infraestructure.db.springdata.repository;

import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.OrdenDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDataRepository extends ReactiveMongoRepository<OrdenDTO, String> {
}
